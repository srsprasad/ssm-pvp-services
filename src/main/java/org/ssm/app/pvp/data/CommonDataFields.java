package org.ssm.app.pvp.data;

public abstract class CommonDataFields {
	
	protected String status;
	protected Integer schoolId;
	protected String schoolName;
	protected String contactNumber;
	protected String emailId;
	protected String submittedOn;
	
	public String getStatus() {
		return status;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getSubmittedOn() {
		return submittedOn;
	}
}
