package com.ibm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *Entity class used to store details about all bookings
 */

@Entity
public class Booking {
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Shows shows;
	private int xPos;
	private int yPos;
	@ManyToOne
	private Payment payment;
	@ManyToOne
	private User user;
	private String type;
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
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
