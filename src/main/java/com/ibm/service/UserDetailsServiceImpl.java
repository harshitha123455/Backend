package com.ibm.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.entity.Admin;
import com.ibm.repo.AdminRepository;

/**
 * Implementation of the Spring Security {@link UserDetailsService} interface
 * that provides user details for authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository repo;

	/**
	 * Loads user details by username (email).
	 *
	 * @param email the email of the user
	 * @return the user details
	 * @throws UsernameNotFoundException if the user with the specified email is not
	 *                                   found
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = repo.findByEmail(email);
		if (admin == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPasswordHash(),
				Collections.emptyList());
	}
}
