package com.ibm.service;

import java.util.List;

import com.ibm.entity.Shows;
import com.ibm.exception.MovieNotFoundException;

/**
 * Service interface for managing movie shows.
 */
public interface ShowService {

	/**
	 * Saves a movie show.
	 *
	 * @param show the movie show to be saved
	 * @return the ID of the saved movie show
	 */
	int save(Shows show);

	/**
	 * Retrieves a list of all movie shows.
	 *
	 * @return a list of Shows entities
	 */
	List<Shows> list();

	/**
	 * Searches for a movie show by ID.
	 *
	 * @param id the ID of the movie show to search for
	 * @return the found Shows entity
	 */
	Shows searchById(int id);

	/**
	 * Searches for movie shows by the ID of the associated movie.
	 *
	 * @param id the ID of the movie to search for shows
	 * @return a list of Shows entities associated with the given movie ID
	 * @throws MovieNotFoundException if the movie with the given ID is not found
	 */
	List<Shows> searchByMovieId(int id) throws MovieNotFoundException;

	/**
	 * Searches for movie shows by the name of the associated movie.
	 *
	 * @param name the name of the movie to search for shows
	 * @return a list of Shows entities associated with the given movie name
	 * @throws MovieNotFoundException if the movie with the given name is not found
	 */
	List<Shows> searchByMovieName(String name) throws MovieNotFoundException;

	/**
	 * Removes a movie show by ID.
	 *
	 * @param id the ID of the movie show to remove
	 */
	void removeById(int id);
}
