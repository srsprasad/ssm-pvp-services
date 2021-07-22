package org.ssm.app.pvp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentData extends CommonDataFields {
	
	private String alumniName;
	private String studentName;
	private String highestClass;	
	private String passoutYear;
	
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
	@JsonProperty("ALUMNI_NAME")
	public void setAlumniName(String alumniName) {
		this.alumniName = alumniName;
	}
	@JsonProperty("STUDENT_NAME")
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@JsonProperty("HIGHEST_PASSOUT_CLASS")
	public void setHighestClass(String highestClass) {
		this.highestClass = highestClass;
	}
	@JsonProperty("PASSOUT_YEAR")
	public void setPassoutYear(String passoutYear) {
		this.passoutYear = passoutYear;
	}
	/*
	 * Super class properties	
	 */	
	public void setStatus(String status) {
		super.status = status;
	}
	@JsonProperty("SCHOOL_ID")
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	@JsonProperty("SCHOOL_NAMES")
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@JsonProperty("STUDENT_CONTACT_NUMBER")
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@JsonProperty("EMAIL_ID")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@JsonProperty("SUBMITTED_ON")
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
}
