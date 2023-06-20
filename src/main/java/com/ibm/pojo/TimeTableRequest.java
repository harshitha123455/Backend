package com.ibm.pojo;

import java.sql.Date;

/**
 * POJO class representing a request for searching a time table.
 */
public class TimeTableRequest {

	private Date date;
	private int sid;

	/**
	 * Retrieves the date for the time table.
	 *
	 * @return The date for the time table.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date for the time table.
	 *
	 * @param date The date to set for the time table.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Retrieves the screen ID for the time table.
	 *
	 * @return The screen ID for the time table.
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * Sets the screen ID for the time table.
	 *
	 * @param sid The screen ID to set for the time table.
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
}
