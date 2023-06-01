package com.ibm.service;

import java.util.List;

import com.ibm.entity.Screen;

public interface ScreenService {

	int save(Screen s) throws ScreenAlreadyExistException;

	List<Screen> list();

	Screen searchById(int id) throws ScreenNotFoundException;

	Screen searchByName(String name) throws ScreenNotFoundException;

	void removeById(int id) throws ScreenNotFoundException;
	
	void removeByName(String name) throws ScreenNotFoundException;
}
