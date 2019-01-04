package com.tfg2018.ws.rest.utils;

import java.io.UnsupportedEncodingException;
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

	public CommandTranslator() {
	}

	public static StringEntity commandToJson(String command, Object... parameters) throws Exception {
		Map<String, Object> mappedCommand = getJsonMap(command, parameters);
		StringEntity jsonResult;
		try {
			jsonResult = new StringEntity(formatJson(mappedCommand));
			return jsonResult;
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error traduciendo elcomando a json");
			throw new Exception(e);
		}
	}

	private static Map<String, Object> getJsonMap(String command, Object... parameters) {
		Map<String, Object> mappedCommand = new HashMap<String, Object>();
		mappedCommand.put("id", UUID.randomUUID().toString());
		mappedCommand.put("method", command.toString().toLowerCase());
		List<Object> paramList = new ArrayList<Object>(Arrays.asList(parameters));
		mappedCommand.put("params", paramList);
		return mappedCommand;
	}

	public static String formatJson(Object value) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return gson.toJson(value);
	}
}
