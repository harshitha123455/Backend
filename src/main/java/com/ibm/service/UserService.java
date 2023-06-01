package com.ibm.service;

import java.util.List;

import com.ibm.entity.User;

public interface UserService {

	int save(User u);

	List<User> list();

	User searchById(int id);

	Boolean remove(int id);
}
