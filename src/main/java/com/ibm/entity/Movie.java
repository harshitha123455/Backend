package com.ibm.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class which store details about available movies
 */
@Entity
public class Movie {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String genre;

	@ElementCollection
	private List<String> reviews;
	private LocalDate releaseDate;
	@ElementCollection
	private List<String> cast;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public void setReviews(List<String> reviews) {
		this.reviews = reviews;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

}
