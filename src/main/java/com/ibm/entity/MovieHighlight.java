package com.ibm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The MovieHighlight class represents an entity that stores details about a
 * movie highlight.
 */
@Entity
public class MovieHighlight {

	@Id
	private int id;
	@ManyToOne
	private Movie movie;
	private String largePosterUrl;

	/**
	 * Retrieves the ID of the movie highlight.
	 *
	 * @return the movie highlight ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the movie highlight.
	 *
	 * @param id the movie highlight ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the movie associated with the movie highlight.
	 *
	 * @return the associated movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * Sets the movie associated with the movie highlight.
	 *
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * Retrieves the URL of the large poster image for the movie highlight.
	 *
	 * @return the large poster image URL
	 */
	public String getLargePosterUrl() {
		return largePosterUrl;
	}

	/**
	 * Sets the URL of the large poster image for the movie highlight.
	 *
	 * @param largePosterUrl the large poster image URL to set
	 */
	public void setLargePosterUrl(String largePosterUrl) {
		this.largePosterUrl = largePosterUrl;
	}

}