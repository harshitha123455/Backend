package com.ibm.service;

import java.util.List;

import com.ibm.entity.Admin;

/**
 * Service interface for managing Admin entities.
 */
public interface AdminService {

	/**
	 * Saves an Admin entity.
	 *
	 * @param ad The Admin entity to be saved.
	 * @return The ID of the saved Admin.
	 */
	int save(Admin ad);

	/**
	 * Retrieves a list of all Admin entities.
	 *
	 * @return A list of Admin entities.
	 */
	List<Admin> list();

	/**
	 * Searches for an Admin entity by ID.
	 *
	 * @param id The ID of the Admin to search for.
	 * @return The found Admin entity, or null if not found.
	 */
	Admin searchById(int id);

	/**
	 * Searches for an Admin entity by email.
	 *
	 * @param email The email of the Admin to search for.
	 * @return The found Admin entity, or null if not found.
	 */
	Admin searchByEmail(String email);

	/**
	 * Removes an Admin entity by ID.
	 *
	 * @param id The ID of the Admin to remove.
	 * @return True if the Admin was successfully removed, false otherwise.
	 */
	Boolean remove(int id);

	/**
	 * Authenticates an Admin using the provided email and password.
	 *
	 * @param email    The email of the Admin.
	 * @param password The password of the Admin.
	 * @return A JWT token if authentication is successful, null otherwise.
	 */
	String authenticate(String email, String password);
}
