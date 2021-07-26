package org.ssm.app.pvp.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifiedAlumniData extends StudentData {
	
	public VerifiedAlumniData() {
		super();
		super.status= "Verified";
	}
	
	@JsonProperty("ALUMNI_NAME")
	public void setAlumniName(String alumniName) {
		this.alumniName = alumniName;
	}
	@JsonProperty("STUDENT_NAME")
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@JsonProperty("PASSOUT_CLASS")
	public void setHighestClass(String highestClass) {
		this.highestClass = highestClass;
	}	
	public void setStatus(String status) {
		super.status = status;
	}
	@JsonProperty("SCHOOL_ID")
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	@JsonProperty("SCHOOL_NAME")
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@JsonProperty("CONTACT_NUMBER")
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@JsonProperty("EMAIL_ID")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return super.toString();
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
}
