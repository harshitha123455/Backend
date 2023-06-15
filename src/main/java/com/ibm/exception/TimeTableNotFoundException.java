package com.ibm.exception;

public class TimeTableNotFoundException extends Exception {

	private static final long serialVersionUID = -4973523961962600190L;

	public TimeTableNotFoundException() {
		super();
	}

	public TimeTableNotFoundException(String message) {
		super(message);
	}
	
	public TimeTableNotFoundException(int id) {
		super("No Timetable found with id: " + id);
	}
	
}
