package com.ibm.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

/**
 * The SeatingArrangement class represents an entity that stores the seat
 * arrangement for all shows.
 */
@Entity
public class SeatingArrangement {

	@Id
	@GeneratedValue
	private int id;
	private int totalSeats;
	private int availableSeats;
	private int availableNormalSeats;
	private int availablePremiumSeats;
	private int availableExecutiveSeats;
	@Column(length = 100)
	@Type(type = "true_false")
	@ElementCollection(targetClass = Boolean.class)
	private List<Boolean> reserved;

	/**
	 * Default constructor for the SeatingArrangement class.
	 */
	public SeatingArrangement() {
		// Default constructor
	}

	/**
	 * Creates a deep copy of the SeatingArrangement object.
	 *
	 * @param sa the SeatingArrangement object to be copied
	 */
	public SeatingArrangement(SeatingArrangement sa) {
		this.totalSeats = sa.getTotalSeats();
		this.availableSeats = sa.getAvailableSeats();
		this.availableNormalSeats = sa.getAvailableNormalSeats();
		this.availablePremiumSeats = sa.getAvailablePremiumSeats();
		this.availableExecutiveSeats = sa.getAvailableExecutiveSeats();
		this.reserved = new ArrayList<>(Collections.nCopies(100, false));
	}

	/**
	 * Retrieves the ID of the seating arrangement.
	 *
	 * @return the seating arrangement ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the seating arrangement.
	 *
	 * @param id the seating arrangement ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the total number of seats in the seating arrangement.
	 *
	 * @return the total number of seats
	 */
	public int getTotalSeats() {
		return totalSeats;
	}

	/**
	 * Sets the total number of seats in the seating arrangement.
	 *
	 * @param totalSeats the total number of seats to set
	 */
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	/**
	 * Retrieves the number of available seats in the seating arrangement.
	 *
	 * @return the number of available seats
	 */
	public int getAvailableSeats() {
		return availableSeats;
	}

	/**
	 * Sets the number of available seats in the seating arrangement.
	 *
	 * @param availableSeats the number of available seats to set
	 */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	/**
	 * Retrieves the number of available normal seats in the seating arrangement.
	 *
	 * @return the number of available normal seats
	 */
	public int getAvailableNormalSeats() {
		return availableNormalSeats;
	}

	/**
	 * Sets the number of available normal seats in the seating arrangement.
	 *
	 * @param availableNormalSeats the number of available normal seats to set
	 */
	public void setAvailableNormalSeats(int availableNormalSeats) {
		this.availableNormalSeats = availableNormalSeats;
	}

	/**
	 * Retrieves the number of available premium seats in the seating arrangement.
	 *
	 * @return the number of available premium seats
	 */
	public int getAvailablePremiumSeats() {
		return availablePremiumSeats;
	}

	/**
	 * Sets the number of available premium seats in the seating arrangement.
	 *
	 * @param availablePremiumSeats the number of available premium seats to set
	 */
	public void setAvailablePremiumSeats(int availablePremiumSeats) {
		this.availablePremiumSeats = availablePremiumSeats;
	}

	/**
	 * Retrieves the number of available executive seats in the seating arrangement.
	 *
	 * @return the number of available executive seats
	 */
	public int getAvailableExecutiveSeats() {
		return availableExecutiveSeats;
	}

	/**
	 * 
	 * 
	 * Sets the number of available executive seats in the seating arrangement.
	 *
	 * @param availableExecutiveSeats the number of available executive seats to set
	 */
	public void setAvailableExecutiveSeats(int availableExecutiveSeats) {
		this.availableExecutiveSeats = availableExecutiveSeats;
	}

	/**
	 * Retrieves the list of reserved seats in the seating arrangement.
	 *
	 * @return the list of reserved seats
	 */
	public List<Boolean> getReserved() {
		return reserved;
	}

	/**
	 * Sets the list of reserved seats in the seating arrangement.
	 *
	 * @param reserved the list of reserved seats to set
	 */
	public void setReserved(List<Boolean> reserved) {
		this.reserved = reserved;
	}

}
