package com.ibm.exception;

/**
 * Exception class to indicate that a movie was not found.
 */
public class MovieNotFoundException extends Exception {

	private static final long serialVersionUID = -8211147337474126009L;

	/**
	 * Constructs a new MovieNotFoundException with no detail message.
	 */
	public MovieNotFoundException() {
		super();
	}

	/**
	 * Constructs a new MovieNotFoundException with the specified movie name.
	 *
	 * @param name The name of the movie that was not found.
	 */
	public MovieNotFoundException(String name) {
		super("No Movie found with name: " + name);
	}

	/**
	 * Constructs a new MovieNotFoundException with the specified movie ID.
	 *
	 * @param id The ID of the movie that was not found.
	 */
	public MovieNotFoundException(int id) {
		super("No Movie found with id: " + id);
	}

}
