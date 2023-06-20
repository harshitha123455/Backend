package com.ibm.exception;

/**
 * Exception class to indicate that a movie already exists.
 */
public class MovieAlreadyExistException extends Exception {

	private static final long serialVersionUID = 3091981073540424265L;

	/**
	 * Constructs a new MovieAlreadyExistException with no detail message.
	 */
	public MovieAlreadyExistException() {
		super();
	}

	/**
	 * Constructs a new MovieAlreadyExistException with the specified movie name.
	 *
	 * @param name The name of the movie that already exists.
	 */
	public MovieAlreadyExistException(String name) {
		super("Movie already exists with name - " + name);
	}

	/**
	 * Constructs a new MovieAlreadyExistException with the specified movie ID.
	 *
	 * @param id The ID of the movie that already exists.
	 */
	public MovieAlreadyExistException(int id) {
		super("Movie already exists with id - " + id);
	}

}
