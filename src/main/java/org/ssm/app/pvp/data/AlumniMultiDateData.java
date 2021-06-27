package org.ssm.app.pvp.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlumniMultiDateData {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;
	
	@JsonProperty("alumniData")
	private AlumniData[] alumniData;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AlumniData[] getAlumniData() {
		return alumniData;
	}

	public void setAlumniData(AlumniData[] alumniData) {
		this.alumniData = alumniData;
	}
	
	

}
