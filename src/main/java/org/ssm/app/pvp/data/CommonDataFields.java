package org.ssm.app.pvp.data;

import java.time.LocalDateTime;

public abstract class CommonDataFields {
	
	protected String status;
	protected Integer schoolId;
	protected String schoolName;
	protected String contactNumber;
	protected String emailId;
	protected String submittedOn;
	protected LocalDateTime submittedOnDateTime;
	
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
	public LocalDateTime getSubmittedOnDateTime() {
		return submittedOnDateTime;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
