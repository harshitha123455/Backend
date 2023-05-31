package com.ibm.entity;

import java.time.LocalDateTime;

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
	private LocalDateTime dateTime;
	@ManyToOne
	private Movie movie;
	@ManyToOne
	private Screen screen;

	@OneToOne
	private SeatingArrangement seatingArrangement;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public SeatingArrangement getSeatingArrangement() {
		return seatingArrangement;
	}

	public void setSeatingArrangement(SeatingArrangement seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}

}
