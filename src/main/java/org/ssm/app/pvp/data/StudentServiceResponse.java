package org.ssm.app.pvp.data;

import java.util.List;

public class StudentServiceResponse {
	
	private String lastUpdateOn = "";
	
	private List<SubmittedAlumniData> submittedData;
	
	private List<VerifiedAlumniData> uploadedData;
	
	private List<VerifiedAlumniData> verifiedData;
	
	private List<VerifiedAlumniData> uploadedSingleData;

	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	public List<SubmittedAlumniData> getSubmittedData() {
		return submittedData;
	}
	
	public void setSubmittedData(List<SubmittedAlumniData> submittedData) {
		this.submittedData = submittedData;
	}

	public List<VerifiedAlumniData> getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(List<VerifiedAlumniData> uploadedData) {
		this.uploadedData = uploadedData;
	}

	public List<VerifiedAlumniData> getVerifiedData() {
		return verifiedData;
	}

	public void setVerifiedData(List<VerifiedAlumniData> verifiedData) {
		this.verifiedData = verifiedData;
	}

	public List<VerifiedAlumniData> getUploadedSingleData() {
		return uploadedSingleData;
	}

	public void setUploadedSingleData(List<VerifiedAlumniData> uploadedSingleData) {
		this.uploadedSingleData = uploadedSingleData;
	}
	
}
