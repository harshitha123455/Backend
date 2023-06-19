package com.ibm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity class used to store details about all bookings
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Shows getShows() {
		return shows;
	}

	public void setShows(Shows shows) {
		this.shows = shows;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<String> getType() {
		return types;
	}

	public void setType(List<String> types) {
		this.types = types;
	}

	public List<Integer> getPos() {
		return pos;
	}

	public void setPos(List<Integer> pos) {
		this.pos = pos;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
	

}
