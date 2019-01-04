package com.tfg2018.ws.rest.object;

public class ResponseMessage {
	private String message;
	
	public ResponseMessage(String name) {
		this.message = name;
	}

	public String getName() {
		return message;
	}
}
