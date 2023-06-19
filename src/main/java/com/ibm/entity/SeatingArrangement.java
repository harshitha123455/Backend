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
 * Entity class which stores the seat arrangement for all shows
 */
@Entity
public class SeatingArrangement{

	@Id
	@GeneratedValue
	private int id;
	private int totalSeats;
	private int availableSeats;
	private int availableNormalSeats;
	private int availablePremiumSeats;
	private int availableExecutiveSeats;
	@Column(length=100)
	@Type(type = "true_false")
	@ElementCollection(targetClass = Boolean.class)
	private List<Boolean> reserved;
	
	public SeatingArrangement() {
		
	}
	
	// deep copying
	public SeatingArrangement(SeatingArrangement sa) {
		this.totalSeats = sa.getTotalSeats();
		this.availableSeats = sa.getAvailableSeats();
		this.availableNormalSeats = sa.getAvailableNormalSeats();
		this.availablePremiumSeats = sa.getAvailablePremiumSeats();
		this.availableExecutiveSeats = sa.getAvailableExecutiveSeats();
		this.reserved = new ArrayList<>(Collections.nCopies(100, false));
	}

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

	public List<Boolean> getReserved() {
		return reserved;
	}

	public void setReserved(List<Boolean> reserved) {
		this.reserved = reserved;
	}

}
