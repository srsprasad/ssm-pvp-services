package org.ssm.app.pvp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.ssm.app.pvp.data.StudentData;
import org.ssm.app.pvp.data.StudentServiceResponse;
import org.ssm.app.pvp.data.SubmittedAlumniData;
import org.ssm.app.pvp.data.VerifiedAlumniData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentDataServiceImpl implements StudentDataService {
	private static final String GOOGLE_FORM_SUBMITTED_DATA_URL = "data/googleform-submitted-data.json";
	private static final String PORTAL_VERIFIED_ALUMNI_DATA_URL = "data/alumni-pvpportal-verified-data.json";
	private static final String PORTAL_UNVERIFIED_ALUMNI_DATA_URL = "data/alumni-pvpportal-unverified-data.json";
	private static final String PORTAL_UNVERIFIED_ALUMNI_SINGLE_DATA_URL = "data/alumni-pvpportal-unverified-single-data.json";
	
	private StudentServiceResponse studentServiceResponse = new StudentServiceResponse();
	
	@Value("${student.records.updatedOn}")
	private String updatedOn;
	
	@PostConstruct
	@Override
	public void loadStudentData() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		ClassPathResource resourceData;
		SubmittedAlumniData[] sumbmittedData;
		VerifiedAlumniData[] portalData;
		List<StudentData> uploadedData = new ArrayList<>();
		
		resourceData = new ClassPathResource(GOOGLE_FORM_SUBMITTED_DATA_URL);
		sumbmittedData = mapper.readValue(resourceData.getInputStream(), SubmittedAlumniData[].class);
		studentServiceResponse.setSubmittedData(Arrays.asList(sumbmittedData));

		resourceData = new ClassPathResource(PORTAL_VERIFIED_ALUMNI_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		studentServiceResponse.setVerifiedData(Arrays.asList(portalData));

		resourceData = new ClassPathResource(PORTAL_UNVERIFIED_ALUMNI_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		Stream.of(portalData).parallel().forEach(element-> element.setStatus("Uploaded"));
		uploadedData.addAll(Arrays.asList(portalData));

		resourceData = new ClassPathResource(PORTAL_UNVERIFIED_ALUMNI_SINGLE_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		Stream.of(portalData).parallel().forEach(element-> element.setStatus("Uploaded"));
		studentServiceResponse.setUploadedSingleData(Arrays.asList(portalData));

		studentServiceResponse.setLastUpdateOn(updatedOn);
		
		List<StudentData> totalPortalData = new ArrayList<>();
		totalPortalData.addAll(studentServiceResponse.getVerifiedData());
		totalPortalData.addAll(uploadedData);
		totalPortalData.addAll(studentServiceResponse.getUploadedSingleData());

		List<StudentData> registredData = studentServiceResponse.getSubmittedData().parallelStream()
				.filter(element -> totalPortalData.contains(element)).collect(Collectors.toList());
		
		List<StudentData> notRegistredData = studentServiceResponse.getSubmittedData().parallelStream()
				.filter(element -> !totalPortalData.contains(element)).collect(Collectors.toList());
		
		totalPortalData.parallelStream().filter(data-> registredData.contains(data)).forEach(element->{
			for(StudentData data: registredData) {
				if(data.equals(element)) {
					data.setStatus(element.getStatus());
					data.setStudentIdManually(element.getStudentId());break;
				}
			}
		});
		List<StudentData> googleFormData = new ArrayList<>();
		
		googleFormData.addAll(registredData);
		googleFormData.addAll(notRegistredData);
		uploadedData.addAll(notRegistredData);
		
		studentServiceResponse.setGoogleFormData(googleFormData);
		studentServiceResponse.setUploadedData(uploadedData);
		
		System.out.println("Total Submitted records: "+ studentServiceResponse.getSubmittedData().size());
		System.out.println("Registred from Submitted records: "+ registredData.size());				
		System.out.println("Remaining Submitted records: "+ notRegistredData.size());
		
	}
	
	@Override
	public StudentServiceResponse getAllStudentRegistrationData() {
		return studentServiceResponse;
	}

}
