package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Movie;

/**
 * Repository interface for managing the Movie entity.
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	/**
	 * Retrieves a Movie entity by its name.
	 *
	 * @param name the name of the movie
	 * @return the Movie entity with the specified name, or null if not found
	 */
	Movie findByName(String name);
}
