package com.ibm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Screen class represents an entity that stores details about available
 * screens.
 */
@Entity
public class Screen {

	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	private int totalSeats;
	private int normalSeats;
	private int premiumSeats;
	private int executiveSeats;

	/**
	 * Retrieves the ID of the screen.
	 *
	 * @return the screen ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the screen.
	 *
	 * @param id the screen ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the screen.
	 *
	 * @return the screen name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the screen.
	 *
	 * @param name the screen name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the total number of seats in the screen.
	 *
	 * @return the total number of seats
	 */
	public int getTotalSeats() {
		return totalSeats;
	}

	/**
	 * Sets the total number of seats in the screen.
	 *
	 * @param totalSeats the total number of seats to set
	 */
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	/**
	 * Retrieves the number of normal seats in the screen.
	 *
	 * @return the number of normal seats
	 */
	public int getNormalSeats() {
		return normalSeats;
	}

	/**
	 * Sets the number of normal seats in the screen.
	 *
	 * @param normalSeats the number of normal seats to set
	 */
	public void setNormalSeats(int normalSeats) {
		this.normalSeats = normalSeats;
	}

	/**
	 * Retrieves the number of premium seats in the screen.
	 *
	 * @return the number of premium seats
	 */
	public int getPremiumSeats() {
		return premiumSeats;
	}

	/**
	 * Sets the number of premium seats in the screen.
	 *
	 * @param premiumSeats the number of premium seats to set
	 */
	public void setPremiumSeats(int premiumSeats) {
		this.premiumSeats = premiumSeats;
	}

	/**
	 * Retrieves the number of executive seats in the screen.
	 *
	 * @return the number of executive seats
	 */
	public int getExecutiveSeats() {
		return executiveSeats;
	}

	/**
	 * Sets the number of executive seats in the screen.
	 *
	 * @param executiveSeats the number of executive seats to set
	 */
	public void setExecutiveSeats(int executiveSeats) {
		this.executiveSeats = executiveSeats;
	}

}