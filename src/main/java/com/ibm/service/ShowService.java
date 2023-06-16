package com.ibm.service;

import java.util.List;

import com.ibm.entity.Shows;
import com.ibm.exception.MovieNotFoundException;

public interface ShowService {

	int save(Shows s);

	List<Shows> list();

	Shows searchById(int id);

	List<Shows> searchByMovieId(int id) throws MovieNotFoundException;
	
	List<Shows> searchByMovieName(String name) throws MovieNotFoundException;

	void removeById(int id);

}
