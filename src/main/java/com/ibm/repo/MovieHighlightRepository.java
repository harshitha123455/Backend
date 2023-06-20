package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.MovieHighlight;

/**
 * Repository interface for managing the MovieHighlight entity.
 */
public interface MovieHighlightRepository extends JpaRepository<MovieHighlight, Integer> {

	// No additional methods are defined as JpaRepository provides basic CRUD
	// operations.
}
