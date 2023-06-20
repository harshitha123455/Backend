package com.ibm.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * The Movie class represents an entity that stores details about a movie.
 */
@Entity
public class Movie {

	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	private LocalTime duration;
	@ElementCollection
	private List<String> director;
	@ElementCollection
	private List<String> genre;
	private String description;
	private LocalDate releaseDate;
	@ElementCollection
	private List<String> cast;
	private String imageUrl;

	/**
	 * 
	 * Retrieves the ID of the movie.
	 * 
	 * @return the movie ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * Sets the ID of the movie.
	 * 
	 * @param id the movie ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * Retrieves the name of the movie.
	 * 
	 * @return the movie name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * Sets the name of the movie.
	 * 
	 * @param name the movie name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * Retrieves the duration of the movie.
	 * 
	 * @return the movie duration
	 */
	public LocalTime getDuration() {
		return duration;
	}

	/**
	 * 
	 * Sets the duration of the movie.
	 * 
	 * @param duration the movie duration to set
	 */
	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	/**
	 * 
	 * Retrieves the directors of the movie.
	 * 
	 * @return the directors of the movie
	 */
	public List<String> getDirector() {
		return director;
	}

	/**
	 * 
	 * Sets the directors of the movie.
	 * 
	 * @param director the directors to set
	 */
	public void setDirector(List<String> director) {
		this.director = director;
	}

	/**
	 * 
	 * Retrieves the genres of the movie.
	 * 
	 * @return the genres of the movie
	 */
	public List<String> getGenre() {
		return genre;
	}

	/**
	 * 
	 * Sets the genres of the movie.
	 * 
	 * @param genre the genres to set
	 */
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	/**
	 * 
	 * Retrieves the description of the movie.
	 * 
	 * @return the movie description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * Sets the description of the movie.
	 * 
	 * @param description the movie description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * Retrieves the release date of the movie.
	 * 
	 * @return the movie release date
	 */
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	/**
	 * 
	 * Sets the release date of the movie.
	 * 
	 * @param releaseDate the movie release date to set
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * 
	 * Retrieves the cast of the movie.
	 * 
	 * @return the cast of the movie
	 */
	public List<String> getCast() {
		return cast;
	}

	/**
	 * 
	 * Sets the cast of the movie.
	 * 
	 * @param cast the cast to set
	 */
	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	/**
	 * 
	 * Retrieves the image URL of the movie.
	 * 
	 * @return the movie image URL
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 
	 * Sets the image URL of the movie.
	 * 
	 * @param imageUrl the movie image URL to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}