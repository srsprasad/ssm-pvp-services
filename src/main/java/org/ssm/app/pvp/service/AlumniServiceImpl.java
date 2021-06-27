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
import org.ssm.app.pvp.data.AlumniData;
import org.ssm.app.pvp.data.AlumniMultiDateData;
import org.ssm.app.pvp.data.BarChartData;
import org.ssm.app.pvp.data.GroupChoiceData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AlumniServiceImpl implements AlumniService {
	
	private AlumniMultiDateData[] alumniStudentsMultiDateData;
	
	private AlumniMultiDateData[] alumniTeachersMultiDateData;
	
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
			
			alumniStudentsMultiDateData = mapper.readValue(studentsData, AlumniMultiDateData[].class);
			Stream.of(alumniStudentsMultiDateData).parallel()
				.forEach(alumniDateData -> {
					Stream.of(alumniDateData.getAlumniData())
					.forEach(data-> {if (data.getDistrict() == null) {data.setDistrict("UNKNOWN");}});
				});
			System.out.println("Number of DataSets found: "+ alumniStudentsMultiDateData.length);
			ClassPathResource teachersResource = new ClassPathResource("data/ssm-alumni-students-data.json");
			String teachersData;
			if (teachersResource.exists()) {
				teachersData = IOUtils.toString(teachersResource.getInputStream(), "utf-8");
			}else {
				throw new IllegalArgumentException("Students data not found");
			}
			alumniTeachersMultiDateData = mapper.readValue(teachersData, AlumniMultiDateData[].class);
			Stream.of(alumniTeachersMultiDateData).parallel()
				.forEach(alumniDateData -> {
					Stream.of(alumniDateData.getAlumniData())
					.forEach(data-> {if (data.getDistrict() == null) {data.setDistrict("UNKNOWN");}});
				});
			System.out.println("Number of DataSets found: "+ alumniTeachersMultiDateData.length);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<GroupChoiceData> alumniMetadataByGroupType() {
		List<GroupChoiceData> groupChoiceDataList = new ArrayList<>();
		// Get latest dated dataset records.
		AlumniData[] alumniData = getMaxDateData().getAlumniData();
		Map<String, Set<AlumniData>> groupedData = Stream.of(alumniData).filter(d -> d.getDistrict() != null)
				.collect(Collectors.groupingBy(AlumniData::getDistrict, Collectors.toSet()));
		System.out.println("Total Datistricts count: " + groupedData.size());
		Integer total = 0;
		for (Map.Entry<String, Set<AlumniData>> entry : groupedData.entrySet()) {
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
		List<String> dates = Stream.of(alumniStudentsMultiDateData).map(amd -> sdf.format(amd.getDate()))
				.collect(Collectors.toList());
		barChartData.setCategories(dates.toArray(new String[dates.size()]));		
		//For students
		List<String> regionsList = Arrays.asList(regions);
		List<BarChartData.Data> alumniData = new ArrayList<>();
		AlumniMultiDateData[] studentsMultiDateData;
		AlumniMultiDateData[] techerssMultiDateData;
		List<AlumniMultiDateData> filtered = new ArrayList<>();
		if (regions != null) {
			Stream.of(alumniStudentsMultiDateData).forEach(schoolData -> {
				List<AlumniData> schoolsData = Stream.of(schoolData.getAlumniData())
						.filter(d -> regionsList.contains(d.getDistrict())).collect(Collectors.toList());
				AlumniMultiDateData multiDateData = new AlumniMultiDateData();
				multiDateData.setDate(schoolData.getDate());
				multiDateData.setAlumniData(schoolsData.toArray(new AlumniData[schoolsData.size()]));
				filtered.add(multiDateData);
			});

			studentsMultiDateData = filtered.toArray(new AlumniMultiDateData[filtered.size()]);
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
				buildBars(alumniStudentsMultiDateData, studentsData, null);
				alumniData.add(studentsData);
			}
			if (includeTeachers) {
				//for Teachers
				final BarChartData.Data teachersData = barChartData.newData();
				teachersData.setName("Teachers");
				buildBars(alumniTeachersMultiDateData, teachersData, null);
				alumniData.add(teachersData);
			}
		}
		
		barChartData.setSeries(alumniData.toArray(new BarChartData.Data[alumniData.size()]));
		return barChartData;
	}
	
	private void buildBars(final AlumniMultiDateData[] multiDateData, final BarChartData.Data processedData, String region) {		
		List<Float> datas = new ArrayList<>();
		Stream.of(multiDateData).forEach(alumniData ->{
			AlumniData[] allSchools = alumniData.getAlumniData();
			Integer totalRegisteredStudents = Stream.of(allSchools)
					.filter(f->(region == null || f.getDistrict().equals(region)))
					.mapToInt(d->d.getTotal()).sum();
			datas.add(Float.parseFloat(totalRegisteredStudents.toString()));
		});		
		processedData.setData(datas.toArray(new Float[datas.size()]));
	}

	private AlumniMultiDateData getMaxDateData() {
		Optional<AlumniMultiDateData> alumniMaxDateData = Stream.of(alumniStudentsMultiDateData)
				.max((left, right) -> left.getDate().compareTo(right.getDate()));
		return alumniMaxDateData.get();
	}

}
