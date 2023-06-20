package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.SeatingArrangement;

/**
 * Repository interface for managing the SeatingArrangement entity.
 */
public interface SeatingArrangementRepository extends JpaRepository<SeatingArrangement, Integer> {

	// No additional methods are defined as JpaRepository provides basic CRUD
	// operations.
}
