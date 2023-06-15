package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.entity.Admin;
import com.ibm.repo.AdminRepository;
import com.ibm.security.jwt.JwtUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int save(Admin a) {
		a.setPasswordHash(passwordEncoder.encode(a.getPassword()));
		repo.save(a);
		return a.getId();
	}

	@Override
	public List<Admin> list() {
		return repo.findAll();
	}

	@Override
	public Admin searchById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public Admin searchByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Boolean remove(int id) {
		repo.deleteById(id);
		return true;
	}

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
