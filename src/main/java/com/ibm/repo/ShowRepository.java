package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Movie;
import com.ibm.entity.Shows;

/**
 * Repository interface for managing the Shows entity.
 */
public interface ShowRepository extends JpaRepository<Shows, Integer> {

	/**
	 * Retrieves a list of shows by the specified movie.
	 *
	 * @param movie The movie object
	 * @return A list of shows associated with the movie
	 */
	List<Shows> findAllByMovie(Movie movie);
}
