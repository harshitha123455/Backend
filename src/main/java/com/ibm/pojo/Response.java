package com.ibm.pojo;

import java.util.List;

public class Response {

	private String message;
	private List<Integer> pos;

	public Response(String message) {
		this.setMessage(message);
	}

	public Response(List<Integer> pos) {
		this.pos = pos;
		System.err.println(this.pos);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Integer> getPos() {
		return pos;
	}

	public void setPos(List<Integer> pos) {
		this.pos = pos;
	}

}
