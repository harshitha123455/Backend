package com.ibm.exception;

public class MovieAlreadyExistException extends Exception {

	private static final long serialVersionUID = 3091981073540424265L;

	public MovieAlreadyExistException() {
		super();
	}

	public MovieAlreadyExistException(String name) {
		super("Movie already exists with name - " + name);
	}

	public MovieAlreadyExistException(int id) {
		super("Movie already exists with id - " + id);
	}

}
