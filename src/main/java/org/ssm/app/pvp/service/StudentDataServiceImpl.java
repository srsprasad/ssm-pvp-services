package org.ssm.app.pvp.service;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
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
		
		resourceData = new ClassPathResource(GOOGLE_FORM_SUBMITTED_DATA_URL);
		sumbmittedData = mapper.readValue(resourceData.getInputStream(), SubmittedAlumniData[].class);
		studentServiceResponse.setSubmittedData(Arrays.asList(sumbmittedData));

		resourceData = new ClassPathResource(PORTAL_VERIFIED_ALUMNI_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		studentServiceResponse.setVerifiedData(Arrays.asList(portalData));

		resourceData = new ClassPathResource(PORTAL_UNVERIFIED_ALUMNI_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		Stream.of(portalData).parallel().forEach(element-> element.setStatus("Uploaded"));
		studentServiceResponse.setUploadedData(Arrays.asList(portalData));

		resourceData = new ClassPathResource(PORTAL_UNVERIFIED_ALUMNI_SINGLE_DATA_URL);
		portalData = mapper.readValue(resourceData.getInputStream(), VerifiedAlumniData[].class);
		Stream.of(portalData).parallel().forEach(element-> element.setStatus("Uploaded"));
		studentServiceResponse.setUploadedSingleData(Arrays.asList(portalData));

		studentServiceResponse.setLastUpdateOn(updatedOn);
		
		studentServiceResponse.getUploadedData().stream()
				.filter(data -> studentServiceResponse.getSubmittedData().contains(data)).toList()
				.forEach(e -> System.out.println(e));
		
	}
	
	@Override
	public StudentServiceResponse getAllStudentRegistrationData() {
		return studentServiceResponse;
	}

}
