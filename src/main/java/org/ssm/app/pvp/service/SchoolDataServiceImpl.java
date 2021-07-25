package org.ssm.app.pvp.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.ssm.app.pvp.data.SchoolData;
import org.ssm.app.pvp.data.SchoolMultiDateData;
import org.ssm.app.pvp.data.BarChartData;
import org.ssm.app.pvp.data.GroupChoiceData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SchoolDataServiceImpl implements SchoolDataService {
	
	private SchoolMultiDateData[] schoolStudentsMultiDateData;
	
	private SchoolMultiDateData[] schoolTeachersMultiDateData;
	
	@PostConstruct
	public void init() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ClassPathResource studentResource = new ClassPathResource("data/ssm-alumni-students-data.json");
			String studentsData;
			if (studentResource.exists()) {
				studentsData = IOUtils.toString(studentResource.getInputStream(), "utf-8");
			}else {
				throw new IllegalArgumentException("Students data not found");
			}
			
			schoolStudentsMultiDateData = mapper.readValue(studentsData, SchoolMultiDateData[].class);
			Stream.of(schoolStudentsMultiDateData).parallel()
				.forEach(alumniDateData -> {
					Stream.of(alumniDateData.getSchoolData())
					.forEach(data-> {if (data.getDistrict() == null) {data.setDistrict("UNKNOWN");}});
				});
			ClassPathResource teachersResource = new ClassPathResource("data/ssm-alumni-students-data.json");
			String teachersData;
			if (teachersResource.exists()) {
				teachersData = IOUtils.toString(teachersResource.getInputStream(), "utf-8");
			}else {
				throw new IllegalArgumentException("Students data not found");
			}
			schoolTeachersMultiDateData = mapper.readValue(teachersData, SchoolMultiDateData[].class);
			Stream.of(schoolTeachersMultiDateData).parallel()
				.forEach(alumniDateData -> {
					Stream.of(alumniDateData.getSchoolData())
					.forEach(data-> {if (data.getDistrict() == null) {data.setDistrict("UNKNOWN");}});
				});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<GroupChoiceData> schoolMetadataByGroupType() {
		List<GroupChoiceData> groupChoiceDataList = new ArrayList<>();
		// Get latest dated dataset records.
		SchoolData[] schoolData = getMaxDateData().getSchoolData();
		Map<String, Set<SchoolData>> groupedData = Stream.of(schoolData).filter(d -> d.getDistrict() != null)
				.collect(Collectors.groupingBy(SchoolData::getDistrict, Collectors.toSet()));
		Integer total = 0;
		for (Map.Entry<String, Set<SchoolData>> entry : groupedData.entrySet()) {
			Integer totalGroupStudents = entry.getValue().stream().mapToInt(p -> p.getTotal()).sum();
			total += totalGroupStudents;
			GroupChoiceData choiceData = new GroupChoiceData();
			choiceData.setGroupShortName(entry.getKey());
			choiceData.setSchoolsCount(entry.getValue().size());
			choiceData.setTotalRegistredStudents(totalGroupStudents);
			groupChoiceDataList.add(choiceData);
		}
		System.out.println("Total Students count: " + total);
		return groupChoiceDataList;
	}
	@Override
	public BarChartData processBarChartData(boolean includeStudents, boolean includeTeachers, String[] regions) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		BarChartData barChartData = new BarChartData();
		List<String> dates = Stream.of(schoolStudentsMultiDateData).map(amd -> sdf.format(amd.getDate()))
				.collect(Collectors.toList());
		barChartData.setCategories(dates.toArray(new String[dates.size()]));		
		//For students
		List<String> regionsList = Arrays.asList(regions);
		List<BarChartData.Data> alumniData = new ArrayList<>();
		SchoolMultiDateData[] studentsMultiDateData;
		SchoolMultiDateData[] techerssMultiDateData;
		List<SchoolMultiDateData> filtered = new ArrayList<>();
		if (regions != null) {
			Stream.of(schoolStudentsMultiDateData).forEach(schoolData -> {
				List<SchoolData> schoolsData = Stream.of(schoolData.getSchoolData())
						.filter(d -> regionsList.contains(d.getDistrict())).collect(Collectors.toList());
				SchoolMultiDateData multiDateData = new SchoolMultiDateData();
				multiDateData.setDate(schoolData.getDate());
				multiDateData.setSchoolData(schoolsData.toArray(new SchoolData[schoolsData.size()]));
				filtered.add(multiDateData);
			});

			studentsMultiDateData = filtered.toArray(new SchoolMultiDateData[filtered.size()]);
			if (includeStudents) {
				for (String region : regionsList) {
					final BarChartData.Data studentsData = barChartData.newData();
					studentsData.setName(region);
					buildBars(studentsMultiDateData, studentsData, region);
					alumniData.add(studentsData);
				}
			}
		}else {
			if (includeStudents) {
				final BarChartData.Data studentsData = barChartData.newData();
				studentsData.setName("Students");
				buildBars(schoolStudentsMultiDateData, studentsData, null);
				alumniData.add(studentsData);
			}
			if (includeTeachers) {
				//for Teachers
				final BarChartData.Data teachersData = barChartData.newData();
				teachersData.setName("Teachers");
				buildBars(schoolTeachersMultiDateData, teachersData, null);
				alumniData.add(teachersData);
			}
		}
		
		barChartData.setSeries(alumniData.toArray(new BarChartData.Data[alumniData.size()]));
		return barChartData;
	}
	
	private void buildBars(final SchoolMultiDateData[] multiDateData, final BarChartData.Data processedData, String region) {		
		List<Integer> datas = new ArrayList<>();
		Stream.of(multiDateData).forEach(dataRecord ->{
			SchoolData[] allSchools = dataRecord.getSchoolData();
			Integer totalRegisteredStudents = Stream.of(allSchools)
					.filter(f->(region == null || f.getDistrict().equals(region)))
					.mapToInt(d->d.getTotal()).sum();
			datas.add(totalRegisteredStudents);
		});		
		processedData.setData(datas);
	}

	private SchoolMultiDateData getMaxDateData() {
		Optional<SchoolMultiDateData> alumniMaxDateData = Stream.of(schoolStudentsMultiDateData)
				.max((left, right) -> left.getDate().compareTo(right.getDate()));
		return alumniMaxDateData.get();
	}

}
