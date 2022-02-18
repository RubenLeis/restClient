package es.caixagalicia.rest.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper<T, U> {

	private ObjectMapper mapper = new ObjectMapper();
	private Class<U> typeParameterClass;

	public JsonMapper(Class<U> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;	
	}

	public String writeValueAsString(T t) throws JsonProcessingException {
		return mapper.writeValueAsString(t);
	}

	public U readValue(String json) throws JsonParseException, JsonMappingException, IOException {
		U newObject = (U) mapper.readValue(json, typeParameterClass);
		return newObject;
	}

}
