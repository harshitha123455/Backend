package com.ibm.service;

public class ScreenNotFoundException extends Exception {

	private static final long serialVersionUID = -3799047831664125511L;

	public ScreenNotFoundException() {
		super();
	}

	public ScreenNotFoundException(String name) {
		super("No Screen found with name: " + name);
	}

	public ScreenNotFoundException(int id) {
		super("No Screen found with id: " + id);
	}

}
