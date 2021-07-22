package org.ssm.app.pvp.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolMultiDateData {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;
	
	@JsonProperty("alumniData")
	private SchoolData[] alumniData;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SchoolData[] getSchoolData() {
		return alumniData;
	}

	public void setSchoolData(SchoolData[] schoolData) {
		this.alumniData = schoolData;
	}
	
	

}
