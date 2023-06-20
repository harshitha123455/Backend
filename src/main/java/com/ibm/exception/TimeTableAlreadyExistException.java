package com.ibm.exception;

/**
 * Exception class to indicate that a timetable already exists.
 */
public class TimeTableAlreadyExistException extends Exception {

	private static final long serialVersionUID = -4665826659903174365L;

	/**
	 * Constructs a new TimeTableAlreadyExistException with no detail message.
	 */
	public TimeTableAlreadyExistException() {
		super();
	}

	/**
	 * Constructs a new TimeTableAlreadyExistException with the specified detail
	 * message.
	 *
	 * @param message The detail message describing the exception.
	 */
	public TimeTableAlreadyExistException(String message) {
		super(message);
	}

}
