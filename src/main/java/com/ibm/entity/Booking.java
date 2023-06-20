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
 * The Booking class represents an entity that stores details about a booking.
 */
@Entity
public class Booking {

	@Id
	@GeneratedValue
	private int id;
	private double amount;
	@ManyToOne
	private Shows shows;
	@ElementCollection
	private List<Integer> pos;
	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@ElementCollection
	private List<String> types;

	/**
	 * Retrieves the ID of the booking.
	 *
	 * @return the booking ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the booking.
	 *
	 * @param id the booking ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the amount of the booking.
	 *
	 * @return the booking amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the booking.
	 *
	 * @param amount the booking amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Retrieves the shows associated with the booking.
	 *
	 * @return the shows associated with the booking
	 */
	public Shows getShows() {
		return shows;
	}

	/**
	 * Sets the shows associated with the booking.
	 *
	 * @param shows the shows to set
	 */
	public void setShows(Shows shows) {
		this.shows = shows;
	}

	/**
	 * Retrieves the positions of the booking.
	 *
	 * @return the positions of the booking
	 */
	public List<Integer> getPos() {
		return pos;
	}

	/**
	 * Sets the positions of the booking.
	 *
	 * @param pos the positions to set
	 */
	public void setPos(List<Integer> pos) {
		this.pos = pos;
	}

	/**
	 * Retrieves the payment associated with the booking.
	 *
	 * @return the payment associated with the booking
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * Sets the payment associated with the booking.
	 *
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * Retrieves the user associated with the booking.
	 *
	 * @return the user associated with the booking
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user associated with the booking.
	 *
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Retrieves the types of the booking.
	 *
	 * @return the types of the booking
	 */
	public List<String> getTypes() {
		return types;
	}

	/**
	 * Sets the types of the booking.
	 *
	 * @param types the types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}
}
