package com.ibm.service;

public class MovieNotFoundException extends Exception {

	private static final long serialVersionUID = -8211147337474126009L;

	public MovieNotFoundException() {
		super();
	}

	public MovieNotFoundException(String name) {
		super("No Movie found with name: " + name);
	}

	public MovieNotFoundException(int id) {
		super("No Movie found with id: " + id);
	}

}
