package io.odilon.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class OdilonObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = 1L;

	public OdilonObjectMapper () {
		super();
		registerModule(new JavaTimeModule());
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		registerModule(new Jdk8Module());
	}
}
