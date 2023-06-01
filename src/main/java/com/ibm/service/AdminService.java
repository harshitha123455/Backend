package com.ibm.service;

import java.util.List;

import com.ibm.entity.Admin;

public interface AdminService {

	int save(Admin a);

	List<Admin> list();

	Admin searchById(int id);

	Boolean remove(int id);
}
