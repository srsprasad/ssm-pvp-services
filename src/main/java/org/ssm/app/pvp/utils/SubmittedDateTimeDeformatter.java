package org.ssm.app.pvp.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class SubmittedDateTimeDeformatter extends StdDeserializer<LocalDateTime> {

	private static DateTimeFormatter multiDateTimeFormatter = new DateTimeFormatterBuilder()
			.appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss"))
			.appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm")).toFormatter();
	
	protected SubmittedDateTimeDeformatter() {
		this(null);
	}
	
	protected SubmittedDateTimeDeformatter(Class<LocalDateTime> vc) {
		super(vc);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		String dateString = p.getText();
		return LocalDateTime.parse(dateString, multiDateTimeFormatter);
	}

}
