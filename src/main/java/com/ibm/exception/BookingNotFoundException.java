package com.ibm.exception;

public class BookingNotFoundException extends Exception {

	private static final long serialVersionUID = 595914675816913896L;

	public BookingNotFoundException() {
		super();
	}

	public BookingNotFoundException(int id) {
		super("No Booking found with id: " + id);
	}

	
}
