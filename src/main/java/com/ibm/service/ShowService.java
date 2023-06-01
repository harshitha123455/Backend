package com.ibm.service;

import java.util.List;

import com.ibm.entity.Movie;
import com.ibm.entity.Shows;

public interface ShowService {

	int save(Shows s);

	List<Shows> list();

	Shows searchById(int id);

	List<Shows> searchByMovie(Movie m);

	Boolean remove(int id);

}
