package com.ibm.pojo;

public class Response {
	
	private String message;
	
	public Response(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
