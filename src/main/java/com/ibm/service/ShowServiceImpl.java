package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Shows;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.ShowRepository;

/**
 * Implementation of the {@link ShowService} interface that manages movie shows.
 */
@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository repo;

	@Autowired
	private MovieService service;

	/**
	 * Saves a movie show.
	 *
	 * @param s the movie show to be saved
	 * @return the ID of the saved movie show
	 */
	@Override
	public int save(Shows s) {
		repo.save(s);
		return s.getId();
	}

	/**
	 * Retrieves a list of all movie shows.
	 *
	 * @return a list of Shows entities
	 */
	@Override
	public List<Shows> list() {
		return repo.findAll();
	}

	/**
	 * Searches for a movie show by ID.
	 *
	 * @param id the ID of the movie show to search for
	 * @return the found Shows entity
	 */
	@Override
	public Shows searchById(int id) {
		return repo.findById(id).get();
	}

	/**
	 * Searches for movie shows by the ID of the associated movie.
	 *
	 * @param id the ID of the movie to search for shows
	 * @return a list of Shows entities associated with the given movie ID
	 * @throws MovieNotFoundException if the movie with the given ID is not found
	 */
	@Override
	public List<Shows> searchByMovieId(int id) throws MovieNotFoundException {
		return repo.findAllByMovie(service.searchById(id));
	}

	/**
	 * Searches for movie shows by the name of the associated movie.
	 *
	 * @param name the name of the movie to search for shows
	 * @return a list of Shows entities associated with the given movie name
	 * @throws MovieNotFoundException if the movie with the given name is not found
	 */
	@Override
	public List<Shows> searchByMovieName(String name) throws MovieNotFoundException {
		return repo.findAllByMovie(service.searchByName(name));
	}

	/**
	 * Removes a movie show by ID.
	 *
	 * @param id the ID of the movie show to remove
	 */
	@Override
	public void removeById(int id) {
		repo.deleteById(id);
	}
}
