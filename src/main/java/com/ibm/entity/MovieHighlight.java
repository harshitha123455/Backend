package com.ibm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovieHighlight {

	@Id
	private int id;
	@ManyToOne
	private Movie movie;
	private String largePosterUrl;

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

	public String getLargePosterUrl() {
		return largePosterUrl;
	}

	public void setLargePosterUrl(String largePosterUrl) {
		this.largePosterUrl = largePosterUrl;
	}

}
