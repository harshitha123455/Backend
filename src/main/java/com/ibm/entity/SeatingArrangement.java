package com.ibm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity class which stores the seat arrangement for all shows
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
	private Boolean[] seats;

	@OneToOne(mappedBy = "seatingArrangement")
	private Shows shows;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Shows getShows() {
		return shows;
	}

	public void setShows(Shows shows) {
		this.shows = shows;
	}

	public int getAvailableNormalSeats() {
		return availableNormalSeats;
	}

	public void setAvailableNormalSeats(int availableNormalSeats) {
		this.availableNormalSeats = availableNormalSeats;
	}

	public int getAvailablePremiumSeats() {
		return availablePremiumSeats;
	}

	public void setAvailablePremiumSeats(int availablePremiumSeats) {
		this.availablePremiumSeats = availablePremiumSeats;
	}

	public int getAvailableExecutiveSeats() {
		return availableExecutiveSeats;
	}

	public void setAvailableExecutiveSeats(int availableExecutiveSeats) {
		this.availableExecutiveSeats = availableExecutiveSeats;
	}

	public Boolean[] getSeats() {
		return seats;
	}

	public void setSeats(Boolean[] seats) {
		this.seats = seats;
	}
	
	

}
