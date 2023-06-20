package com.ibm.exception;

/**
 * Exception class to indicate that a screen is not found.
 */
public class ScreenNotFoundException extends Exception {

	private static final long serialVersionUID = -3799047831664125511L;

	/**
	 * Constructs a new ScreenNotFoundException with no detail message.
	 */
	public ScreenNotFoundException() {
		super();
	}

	/**
	 * Constructs a new ScreenNotFoundException with the specified screen name.
	 *
	 * @param name The name of the screen that was not found.
	 */
	public ScreenNotFoundException(String name) {
		super("No Screen found with name: " + name);
	}

	/**
	 * Constructs a new ScreenNotFoundException with the specified screen ID.
	 *
	 * @param id The ID of the screen that was not found.
	 */
	public ScreenNotFoundException(int id) {
		super("No Screen found with id: " + id);
	}

}
