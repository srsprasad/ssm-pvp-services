package org.ssm.app.pvp.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlumniData {

	@JsonProperty("SCHOOL_ID")
	private Integer schoolId;
	
	@JsonProperty("SCHOOL_NAME")
	private String schoolName;
	
    @JsonProperty("SCHOOL_ESTABLISHED_YEAR")
	private Integer schoolEstablishedYear;
	
    @JsonProperty("EMAIL_ID")
	private String emailId;
	
    @JsonProperty("CONTACT_NUMBER_1")
	private String contactNumberOne;
	
    @JsonProperty("CONTACT_NUMBER_2")
	private String contactNumberTwo;
	
    @JsonProperty("SCHOOL_FUNCTIONAL_STATUS")
	private String schoolFunctioningStatus;
	
    @JsonProperty("HIGHEST_CLASS")
	private String highestClass;
	
    @JsonProperty("TOTAL")
	private Integer total;
	
    @JsonProperty(value="DISTRICT", defaultValue = "UNKNOWN")
	private String district;

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getSchoolEstablishedYear() {
		return schoolEstablishedYear;
	}

	public void setSchoolEstablishedYear(Integer schoolEstablishedYear) {
		this.schoolEstablishedYear = schoolEstablishedYear;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumberOne() {
		return contactNumberOne;
	}

	public void setContactNumberOne(String contactNumberOne) {
		this.contactNumberOne = contactNumberOne;
	}

	public String getContactNumberTwo() {
		return contactNumberTwo;
	}

	public void setContactNumberTwo(String contactNumberTwo) {
		this.contactNumberTwo = contactNumberTwo;
	}

	public String getSchoolFunctioningStatus() {
		return schoolFunctioningStatus;
	}

	public void setSchoolFunctioningStatus(String schoolFunctioningStatus) {
		this.schoolFunctioningStatus = schoolFunctioningStatus;
	}

	public String getHighestClass() {
		return highestClass;
	}

	public void setHighestClass(String highestClass) {
		this.highestClass = highestClass;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
}
