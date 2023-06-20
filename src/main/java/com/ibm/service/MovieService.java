package com.ibm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;

/**
 * Service interface for managing movies.
 */
public interface MovieService {

	/**
	 * Saves a new movie with the provided details and image.
	 *
	 * @param movie the movie entity to be saved
	 * @param image the image file of the movie
	 * @return the ID of the saved movie
	 * @throws MovieAlreadyExistException if a movie with the same name already
	 *                                    exists
	 */
	int save(Movie movie, MultipartFile image) throws MovieAlreadyExistException;

	/**
	 * Updates an existing movie with the provided details.
	 *
	 * @param movie the updated movie entity
	 * @return the ID of the updated movie
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	int update(Movie movie) throws MovieNotFoundException;

	/**
	 * Retrieves a list of all movies.
	 *
	 * @return a list of Movie entities
	 */
	List<Movie> list();

	/**
	 * Searches for a movie by ID.
	 *
	 * @param id the ID of the movie to search for
	 * @return the found Movie entity
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	Movie searchById(int id) throws MovieNotFoundException;

	/**
	 * Searches for a movie by name.
	 *
	 * @param name the name of the movie to search for
	 * @return the found Movie entity
	 * @throws MovieNotFoundException if the movie with the specified name is not
	 *                                found
	 */
	Movie searchByName(String name) throws MovieNotFoundException;

	/**
	 * Removes a movie by ID.
	 *
	 * @param id the ID of the movie to remove
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	void removeById(int id) throws MovieNotFoundException;

	/**
	 * Removes a movie by name.
	 *
	 * @param name the name of the movie to remove
	 * @throws MovieNotFoundException if the movie with the specified name is not
	 *                                found
	 */
	void removeByName(String name) throws MovieNotFoundException;
}
