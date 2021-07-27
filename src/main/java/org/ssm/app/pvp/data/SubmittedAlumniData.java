package org.ssm.app.pvp.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmittedAlumniData extends StudentData {

	public SubmittedAlumniData() {
		super();
		super.status="Submitted";
	}
	
	@JsonProperty("ALUMNI_NAME")
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
	@JsonProperty("TIMESTAMP")
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
		return (studentName.replaceAll("\\s", "").equalsIgnoreCase(((StudentData) other).getStudentName().replaceAll("\\s", "")) && schoolId.equals(((StudentData)other).getSchoolId()));
	}
}
