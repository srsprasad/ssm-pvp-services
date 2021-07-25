package org.ssm.app.pvp.service;

import org.ssm.app.pvp.data.StudentServiceResponse;

public interface StudentDataService {
	
	public void loadStudentData() throws Exception;
	
	public StudentServiceResponse getAllStudentRegistrationData();
}
