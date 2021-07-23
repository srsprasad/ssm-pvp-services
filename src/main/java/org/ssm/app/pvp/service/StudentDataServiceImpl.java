package org.ssm.app.pvp.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.ssm.app.pvp.data.StudentData;
import org.ssm.app.pvp.data.SubmittedAlumniData;
import org.ssm.app.pvp.data.VerifiedAlumniData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentDataServiceImpl implements StudentDataService {
	private static final String GOOGLE_FORM_SUBMITTED_DATA_URL = "https://srsprasad.github.io/ssm-data/googleform-submitted-data.json";
	private static final String PORTAL_VERIFIED_ALUMNI_DATA_URL = "https://srsprasad.github.io/ssm-data/alumni-pvpportal-verified-data.json";
	private static final String PORTAL_UNVERIFIED_ALUMNI_DATA_URL = "https://srsprasad.github.io/ssm-data/alumni-pvpportal-unverified-data.json";
	private static final String PORTAL_UNVERIFIED_ALUMNI_SINGLE_DATA_URL = "https://srsprasad.github.io/ssm-data/alumni-pvpportal-unverified-single-data.json";
	private List<StudentData> studentRegistrationData = new ArrayList<>();
	
	@PostConstruct
	@Override
	public void loadStudentData() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SubmittedAlumniData[] submittedData = mapper.readValue(new URL(GOOGLE_FORM_SUBMITTED_DATA_URL), SubmittedAlumniData[].class);
		VerifiedAlumniData[] verifiedData = mapper.readValue(new URL(PORTAL_VERIFIED_ALUMNI_DATA_URL), VerifiedAlumniData[].class);
		VerifiedAlumniData[] unverifiedData = mapper.readValue(new URL(PORTAL_UNVERIFIED_ALUMNI_DATA_URL), VerifiedAlumniData[].class);
		Stream.of(unverifiedData).parallel().forEach(d->d.setStatus("Uploaded"));
		VerifiedAlumniData[] unverifiedSingleData = mapper.readValue(new URL(PORTAL_UNVERIFIED_ALUMNI_SINGLE_DATA_URL), VerifiedAlumniData[].class);
		Stream.of(unverifiedSingleData).parallel().forEach(d->d.setStatus("Uploaded"));
		
		studentRegistrationData.addAll(Arrays.asList(submittedData));
		studentRegistrationData.addAll(Arrays.asList(verifiedData));
		studentRegistrationData.addAll(Arrays.asList(unverifiedData));
		studentRegistrationData.addAll(Arrays.asList(unverifiedSingleData));
	}
	
	@Override
	public List<StudentData> getAllStudentRegistrationData() {
		return studentRegistrationData;
	}

}
