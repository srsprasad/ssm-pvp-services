package org.ssm.app.pvp.data;

import java.util.List;

public class StudentServiceResponse {
	
	private String lastUpdateOn = "";
	
	private List<StudentData> submittedData;
	
	private List<StudentData> uploadedData;
	
	private List<StudentData> verifiedData;
	
	private List<StudentData> uploadedSingleData;
	
	private List<StudentData> googleFormData;

	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	public List<StudentData> getSubmittedData() {
		return submittedData;
	}

	public void setSubmittedData(List<StudentData> submittedData) {
		this.submittedData = submittedData;
	}

	public List<StudentData> getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(List<StudentData> uploadedData) {
		this.uploadedData = uploadedData;
	}

	public List<StudentData> getVerifiedData() {
		return verifiedData;
	}

	public void setVerifiedData(List<StudentData> verifiedData) {
		this.verifiedData = verifiedData;
	}

	public List<StudentData> getUploadedSingleData() {
		return uploadedSingleData;
	}

	public void setUploadedSingleData(List<StudentData> uploadedSingleData) {
		this.uploadedSingleData = uploadedSingleData;
	}
	
	public List<StudentData> getGoogleFormData() {
		return googleFormData;
	}
	
	public void setGoogleFormData(List<StudentData> googleFormData) {
		this.googleFormData = googleFormData;
	}
	
}
