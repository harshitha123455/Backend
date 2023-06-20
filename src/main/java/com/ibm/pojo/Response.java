package com.ibm.pojo;

import java.util.List;

/**
 * POJO class representing a response containing a message and a list of
 * positions.
 */
public class Response {

	private String message;
	private List<Integer> pos;

	/**
	 * Constructs a new Response object with the specified message.
	 *
	 * @param message The response message.
	 */
	public Response(String message) {
		this.setMessage(message);
	}

	/**
	 * Constructs a new Response object with the specified list of positions.
	 *
	 * @param pos The list of positions.
	 */
	public Response(List<Integer> pos) {
		this.pos = pos;
		System.err.println(this.pos);
	}

	/**
	 * Retrieves the response message.
	 *
	 * @return The response message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the response message.
	 *
	 * @param message The response message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retrieves the list of positions.
	 *
	 * @return The list of positions.
	 */
	public List<Integer> getPos() {
		return pos;
	}

	/**
	 * Sets the list of positions.
	 *
	 * @param pos The list of positions to set.
	 */
	public void setPos(List<Integer> pos) {
		this.pos = pos;
	}

}
