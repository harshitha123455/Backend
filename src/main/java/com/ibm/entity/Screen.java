package com.ibm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class which store details about available screens
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

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getNormalSeats() {
		return normalSeats;
	}

	public void setNormalSeats(int normalSeats) {
		this.normalSeats = normalSeats;
	}

	public int getPremiumSeats() {
		return premiumSeats;
	}

	public void setPremiumSeats(int premiumSeats) {
		this.premiumSeats = premiumSeats;
	}

	public int getExecutiveSeats() {
		return executiveSeats;
	}

	public void setExecutiveSeats(int executiveSeats) {
		this.executiveSeats = executiveSeats;
	}

}
