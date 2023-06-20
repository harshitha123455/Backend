package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.entity.Admin;
import com.ibm.repo.AdminRepository;
import com.ibm.security.jwt.JwtUtil;

/**
 * Implementation of the {@link AdminService} interface that provides methods
 * for admin operations.
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Saves the admin to the repository. Encrypts the admin's password before
	 * saving.
	 *
	 * @param a the admin to save
	 * @return the ID of the saved admin
	 */
	@Override
	public int save(Admin a) {
		a.setPasswordHash(passwordEncoder.encode(a.getPassword()));
		repo.save(a);
		return a.getId();
	}

	/**
	 * Retrieves a list of all admins.
	 *
	 * @return a list of admins
	 */
	@Override
	public List<Admin> list() {
		return repo.findAll();
	}

	/**
	 * Searches for an admin by ID.
	 *
	 * @param id the ID of the admin
	 * @return the admin with the given ID, or null if not found
	 */
	@Override
	public Admin searchById(int id) {
		return repo.findById(id).get();
	}

	/**
	 * Searches for an admin by email.
	 *
	 * @param email the email of the admin
	 * @return the admin with the given email, or null if not found
	 */
	@Override
	public Admin searchByEmail(String email) {
		return repo.findByEmail(email);
	}

	/**
	 * Removes an admin from the repository.
	 *
	 * @param id the ID of the admin to remove
	 * @return true if the admin was successfully removed, false otherwise
	 */
	@Override
	public Boolean remove(int id) {
		repo.deleteById(id);
		return true;
	}

	/**
	 * Authenticates an admin based on the provided email and password. If the
	 * authentication is successful, generates and returns an authentication token.
	 *
	 * @param email    the email of the admin
	 * @param password the password of the admin
	 * @return the authentication token if authentication is successful, null
	 *         otherwise
	 */
	@Override
	public String authenticate(String email, String password) {
		Admin admin = repo.findByEmail(email);

		if (admin != null && passwordEncoder.matches(password, admin.getPasswordHash())) {
			// Authentication successful
			// Generate and return the authentication token
			String token = JwtUtil.generateToken(admin.getEmail());
			return token;
		}

		// Authentication failed
		return null;
	}
}
