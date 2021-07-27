package org.ssm.app.pvp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentData extends CommonDataFields {

	protected Integer studentId;
	protected String studentName;
	protected String highestClass;
	protected String passoutYear;

	public Integer getStudentId() {
		return studentId;
	}
	public String getStudentName() {
		return studentName;
	}

	public String getHighestClass() {
		return highestClass;
	}

	public String getPassoutYear() {
		return passoutYear;
	}
	
	public void setStudentIdManually(Integer studentId) {
		this.studentId = studentId;
	}
}
