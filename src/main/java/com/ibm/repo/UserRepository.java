package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.User;

/**
 * Repository interface for managing the User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	// No additional methods are defined as JpaRepository provides basic CRUD
	// operations.
}
