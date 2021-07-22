package org.ssm.app.pvp.service;

import java.util.List;

import org.ssm.app.pvp.data.StudentData;

public interface StudentDataService {
	
	public void loadStudentData() throws Exception;
	
	public List<StudentData> getAllStudentRegistrationData();
}
