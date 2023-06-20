package com.ibm.exception;

/**
 * Exception class to indicate that a screen already exists.
 */
public class ScreenAlreadyExistException extends Exception {

	private static final long serialVersionUID = -5842406115531604254L;

	/**
	 * Constructs a new ScreenAlreadyExistException with no detail message.
	 */
	public ScreenAlreadyExistException() {
		super();
	}

	/**
	 * Constructs a new ScreenAlreadyExistException with the specified screen name.
	 *
	 * @param name The name of the screen that already exists.
	 */
	public ScreenAlreadyExistException(String name) {
		super("Screen already exists with name: " + name);
	}

	/**
	 * Constructs a new ScreenAlreadyExistException with the specified screen ID.
	 *
	 * @param id The ID of the screen that already exists.
	 */
	public ScreenAlreadyExistException(int id) {
		super("Screen already exists with id: " + id);
	}

}
