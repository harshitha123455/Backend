package com.ibm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *Entity class which store user details when booking a ticket
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(length = 10)
	private String contactNumber;
	private String email;
	@ElementCollection
	List<Integer> bookingId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public List<Integer> getBookingId() {
		return bookingId;
	}
	public void setBookings(List<Integer> bookingId) {
		this.bookingId = bookingId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
