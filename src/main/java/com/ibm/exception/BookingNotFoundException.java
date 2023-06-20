package com.ibm.exception;

/**
 * Exception class to indicate that a booking was not found.
 */
public class BookingNotFoundException extends Exception {

	private static final long serialVersionUID = 595914675816913896L;

	/**
	 * Constructs a new BookingNotFoundException with no detail message.
	 */
	public BookingNotFoundException() {
		super();
	}

	/**
	 * Constructs a new BookingNotFoundException with the specified detail message.
	 *
	 * @param id The ID of the booking that was not found.
	 */
	public BookingNotFoundException(int id) {
		super("No Booking found with id: " + id);
	}

}
