package com.ibm.pojo;

public class Response {
	
	private String message;
	private double id;
	
	public Response(String message) {
		this.setMessage(message);
	}

	public Response(int id) {
		this.setId(id);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

}
