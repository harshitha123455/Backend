package com.ibm.service;

import java.util.List;

import com.ibm.entity.Screen;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;

public interface ScreenService {

	int save(Screen s) throws ScreenAlreadyExistException;
	
	int update(Screen s) throws ScreenNotFoundException, ScreenAlreadyExistException;

	List<Screen> list();

	Screen searchById(int id) throws ScreenNotFoundException;

	Screen searchByName(String name) throws ScreenNotFoundException;

	void removeById(int id) throws ScreenNotFoundException;
	
	void removeByName(String name) throws ScreenNotFoundException;
}
