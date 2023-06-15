package com.ibm.exception;

public class ScreenAlreadyExistException extends Exception {

	private static final long serialVersionUID = -5842406115531604254L;

	public ScreenAlreadyExistException() {
		super();
	}

	public ScreenAlreadyExistException(String name) {
		super("Screen already exists with name: " + name);
	}
	
	public ScreenAlreadyExistException(int id) {
		super("Screen already exists with id: " + id);
	}

}
