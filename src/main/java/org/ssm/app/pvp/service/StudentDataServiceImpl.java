package org.ssm.app.pvp.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.ssm.app.pvp.data.StudentData;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentDataServiceImpl implements StudentDataService {
	
	private List<StudentData> studentRegistrationData = new ArrayList<>();
	
	@PostConstruct
	@Override
	public void loadStudentData() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		StudentData[] parsedData = mapper.readValue(new URL("https://srsprasad.github.io/ssm-data/googleform-submitted-data.json"), StudentData[].class);
		Stream.of(parsedData).parallel().forEach(d->d.setStatus("Submitted"));
		studentRegistrationData.addAll(Arrays.asList(parsedData));
	}
	
	@Override
	public List<StudentData> getAllStudentRegistrationData() {
		return studentRegistrationData;
	}

}
