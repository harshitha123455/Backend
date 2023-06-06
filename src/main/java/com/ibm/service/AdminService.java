package com.ibm.service;

import java.util.List;

import com.ibm.entity.Admin;
import com.ibm.pojo.AdminDetails;

public interface AdminService {

	int save(AdminDetails ad);

	List<Admin> list();

	Admin searchById(int id);
	
	Admin searchByEmail(String email);

	Boolean remove(int id);
	
	String authenticate(String email, String password);
}
