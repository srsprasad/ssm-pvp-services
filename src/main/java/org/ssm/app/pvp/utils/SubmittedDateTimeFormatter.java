package org.ssm.app.pvp.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class SubmittedDateTimeFormatter extends StdSerializer<LocalDateTime> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	public SubmittedDateTimeFormatter() {
		this(null);
	}
	public SubmittedDateTimeFormatter(Class<LocalDateTime> t) {
		super(t);
	}
	
	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(dateTimeFormatter.format(value));
	}
}
