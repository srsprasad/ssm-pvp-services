package org.ssm.app.pvp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentData extends CommonDataFields {
	
	protected String alumniName;
	protected String studentName;
	protected String highestClass;	
	protected String passoutYear;
	
	public String getAlumniName() {
		return alumniName;
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
}
