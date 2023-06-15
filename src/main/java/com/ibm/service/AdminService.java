package com.ibm.service;

import java.util.List;

import com.ibm.entity.Admin;

public interface AdminService {

	int save(Admin ad);

	List<Admin> list();

	Admin searchById(int id);
	
	Admin searchByEmail(String email);

	Boolean remove(int id);
	
	String authenticate(String email, String password);
}
