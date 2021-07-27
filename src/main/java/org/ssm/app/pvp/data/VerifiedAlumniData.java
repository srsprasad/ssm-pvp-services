package org.ssm.app.pvp.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifiedAlumniData extends StudentData {
	
	public VerifiedAlumniData() {
		super();
		super.status= "Verified";
	}
		
	@JsonProperty("ALUMNI_ID")
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	@JsonProperty("ALUMNI_NAME")
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@JsonProperty("PASSOUT_CLASS")
	public void setHighestClass(String highestClass) {
		this.highestClass = highestClass;
	}
	
	@Override
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
	@JsonProperty("SUBMITTED_ON")
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
	
	@Override
	public int hashCode() {
		return schoolId.hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	@Override
	public boolean equals(Object other) {
		return (studentName.replaceAll("\\s", "")
				.equalsIgnoreCase(((StudentData) other).getStudentName().replaceAll("\\s", ""))
				&& schoolId.equals(((StudentData) other).getSchoolId()));
	}
}
