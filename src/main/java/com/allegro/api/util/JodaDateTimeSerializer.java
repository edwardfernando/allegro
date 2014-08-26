package com.allegro.api.util;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author Edward Fernando
 * 
 *         Codes taken from :
 *         http://ruckus.tumblr.com/post/15588142449/java-joda
 *         -datetime-on-the-wire-long-values-in-the For future use, please refer
 *         to that link
 * 
 */

public class JodaDateTimeSerializer extends JsonSerializer<DateTime> {

	@Override
	public void serialize(DateTime value, JsonGenerator generator, SerializerProvider provider)
	        throws IOException, JsonProcessingException {
		generator.writeObject(value.toDate());
	}

}
