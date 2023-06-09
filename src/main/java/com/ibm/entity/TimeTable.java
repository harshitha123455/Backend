package com.ibm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TimeTable {

	@Id
	@GeneratedValue
	private int id;
	private Date date;
	@ManyToOne
	private Screen screen;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot1;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot2;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot3;
	@OneToOne(cascade = { CascadeType.ALL })
	private Shows slot4;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	public Shows getSlot1() {
		return slot1;
	}
	public void setSlot1(Shows slot1) {
		this.slot1 = slot1;
	}
	public Shows getSlot2() {
		return slot2;
	}
	public void setSlot2(Shows slot2) {
		this.slot2 = slot2;
	}
	public Shows getSlot3() {
		return slot3;
	}
	public void setSlot3(Shows slot3) {
		this.slot3 = slot3;
	}
	public Shows getSlot4() {
		return slot4;
	}
	public void setSlot4(Shows slot4) {
		this.slot4 = slot4;
	}
	
	
}
