package com.ibm.service;

import java.util.List;

import com.ibm.entity.Movie;

public interface MovieService {

	int save(Movie m) throws MovieAlreadyExistException;

	List<Movie> list();

	Movie searchById(int id) throws MovieNotFoundException;

	Movie searchByName(String name) throws MovieNotFoundException;

	void removeById(int id) throws MovieNotFoundException;

	void removeByName(String name) throws MovieNotFoundException;
}
