package com.tfg2018.ws.rest.fchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommandTranslator {
	
	public CommandTranslator() {}
	
	public StringEntity commandToJson(String command, Object... parameters) throws Exception {
		// Generate Mapping of calling arguments
		Map<String, Object> entityValues = getJsonMap(command, parameters);
		// Generate the entity and initialize request
		StringEntity jsonResult = new StringEntity(formatJson(entityValues));
		return jsonResult;
	}
	
	private Map<String, Object> getJsonMap(String command, Object... parameters) {
		Map<String, Object> entityValues = new HashMap<String, Object>();
		entityValues.put("id", UUID.randomUUID().toString());
		entityValues.put("method", command.toString().toLowerCase());
		List<Object> paramList = new ArrayList<Object>(Arrays.asList(parameters));
		entityValues.put("params", paramList);
		return entityValues;
	}
	
	private static String formatJson(Object value) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return gson.toJson(value);
	}
}
