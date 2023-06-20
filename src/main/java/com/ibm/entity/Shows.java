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
 * Entity class which stores details about all the shows.
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
	private String time;

	@ElementCollection
	List<Booking> bookings;

	/**
	 * Retrieves the ID of the show.
	 *
	 * @return The ID of the show.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the show.
	 *
	 * @param id The ID to set for the show.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the movie associated with the show.
	 *
	 * @return The movie associated with the show.
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * Sets the movie associated with the show.
	 *
	 * @param movie The movie to associate with the show.
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * Retrieves the seating arrangement for the show.
	 *
	 * @return The seating arrangement for the show.
	 */
	public SeatingArrangement getSeatingArrangement() {
		return seatingArrangement;
	}

	/**
	 * Sets the seating arrangement for the show.
	 *
	 * @param seatingArrangement The seating arrangement to set for the show.
	 */
	public void setSeatingArrangement(SeatingArrangement seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}

	/**
	 * Retrieves the normal rate for the show.
	 *
	 * @return The normal rate for the show.
	 */
	public double getNormalRate() {
		return normalRate;
	}

	/**
	 * Sets the normal rate for the show.
	 *
	 * @param normalRate The normal rate to set for the show.
	 */
	public void setNormalRate(double normalRate) {
		this.normalRate = normalRate;
	}

	/**
	 * Retrieves the executive rate for the show.
	 *
	 * @return The executive rate for the show.
	 */
	public double getExecutiveRate() {
		return executiveRate;
	}

	/**
	 * Sets the executive rate for the show.
	 *
	 * @param executiveRate The executive rate to set for the show.
	 */
	public void setExecutiveRate(double executiveRate) {
		this.executiveRate = executiveRate;
	}

	/**
	 * Retrieves the premium rate for the show.
	 *
	 * @return The premium rate for the show.
	 */
	public double getPremiumRate() {
		return premiumRate;
	}

	/**
	 * Sets the premium rate for the show.
	 *
	 * @param premiumRate The premium rate to set for the show.
	 */
	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

	/**
	 * Retrieves the time of the show.
	 *
	 * @return The time of the show.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time of the show.
	 *
	 * @param time The time to set for the show.
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
