package com.ibm.exception;

/**
 * Exception class to indicate that a timetable was not found.
 */
public class TimeTableNotFoundException extends Exception {

	private static final long serialVersionUID = -4973523961962600190L;

	/**
	 * Constructs a new TimeTableNotFoundException with no detail message.
	 */
	public TimeTableNotFoundException() {
		super();
	}

	/**
	 * Constructs a new TimeTableNotFoundException with the specified detail
	 * message.
	 *
	 * @param message The detail message describing the exception.
	 */
	public TimeTableNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a new TimeTableNotFoundException with the specified timetable ID.
	 *
	 * @param id The ID of the timetable that was not found.
	 */
	public TimeTableNotFoundException(int id) {
		super("No Timetable found with id: " + id);
	}
}
