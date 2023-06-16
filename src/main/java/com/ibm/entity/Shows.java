package com.ibm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity class which stores details about all the shows
 */
@Entity
public class Shows {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Movie movie;
	@OneToOne(cascade = { CascadeType.ALL })
	private SeatingArrangement seatingArrangement;
	private double normalRate;
	private double executiveRate;
	private double premiumRate;
	
	@ElementCollection
	List<Booking> bookings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public SeatingArrangement getSeatingArrangement() {
		return seatingArrangement;
	}

	public void setSeatingArrangement(SeatingArrangement seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}

	public double getNormalRate() {
		return normalRate;
	}

	public void setNormalRate(double normalRate) {
		this.normalRate = normalRate;
	}

	public double getExecutiveRate() {
		return executiveRate;
	}

	public void setExecutiveRate(double executiveRate) {
		this.executiveRate = executiveRate;
	}

	public double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

}
