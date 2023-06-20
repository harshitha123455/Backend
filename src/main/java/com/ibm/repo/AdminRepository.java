package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Admin;

/**
 * Repository interface for managing the Admin entity.
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	/**
	 * Retrieves an Admin entity by email.
	 *
	 * @param email The email of the Admin.
	 * @return The Admin entity with the specified email, or null if not found.
	 */
	Admin findByEmail(String email);
}
