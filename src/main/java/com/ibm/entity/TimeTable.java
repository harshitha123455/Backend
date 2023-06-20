package com.ibm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity class which stores the timetable for movie showings
 */
@Entity
public class TimeTable {

	@Id
	@GeneratedValue
	private int id;
	private Date date;
	@ManyToOne
	private Screen screen;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot1;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot2;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot3;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot4;

	/**
	 * Retrieves the ID of the timetable.
	 *
	 * @return The ID of the timetable.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the timetable.
	 *
	 * @param id The ID to set for the timetable.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the date of the timetable.
	 * 
	 * @return The date of the timetable.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of the timetable.
	 * 
	 * @param date The date to set for the timetable.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Retrieves the screen associated with the timetable.
	 * 
	 * @return The screen associated with the timetable.
	 */
	public Screen getScreen() {
		return screen;
	}

	/**
	 * Sets the screen for the timetable.
	 * 
	 * @param screen The screen to set for the timetable.
	 */
	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	/**
	 * Retrieves the show in slot 1 of the timetable.
	 * 
	 * @return The show in slot 1 of the timetable.
	 */
	public Shows getSlot1() {
		return slot1;
	}

	/**
	 * Sets the show in slot 1 of the timetable.
	 * 
	 * @param slot1 The show to set in slot 1 of the timetable.
	 */
	public void setSlot1(Shows slot1) {
		this.slot1 = slot1;
	}

	/**
	 * Retrieves the show in slot 2 of the timetable.
	 * 
	 * @return The show in slot 2 of the timetable.
	 */
	public Shows getSlot2() {
		return slot2;
	}

	/**
	 * Sets the show in slot 2 of the timetable.
	 * 
	 * @param slot2 The show to set in slot 2 of the timetable.
	 */
	public void setSlot2(Shows slot2) {
		this.slot2 = slot2;
	}

	/**
	 * Retrieves the show in slot 3 of the timetable.
	 * 
	 * @return The show in slot 3 of the timetable.
	 */
	public Shows getSlot3() {
		return slot3;
	}

	/**
	 * Sets the show in slot 3 of the timetable.
	 * 
	 * @param slot3 The show to set in slot 3 of the timetable.
	 */
	public void setSlot3(Shows slot3) {
		this.slot3 = slot3;
	}

	/**
	 * Retrieves the show in slot 4 of the timetable.
	 * 
	 * @return The show in slot 4 of the timetable.
	 */
	public Shows getSlot4() {
		return slot4;
	}

	/**
	 * Sets the show in slot 4 of the timetable.
	 * 
	 * @param slot4 The show to set in slot 4 of the timetable.
	 */
	public void setSlot4(Shows slot4) {
		this.slot4 = slot4;
	}
}
