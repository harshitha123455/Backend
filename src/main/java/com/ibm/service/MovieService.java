package com.ibm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;

public interface MovieService {

	int save(Movie m, MultipartFile image) throws MovieAlreadyExistException;
	
	int update(Movie m, MultipartFile image) throws MovieNotFoundException;

	List<Movie> list();

	Movie searchById(int id) throws MovieNotFoundException;

	Movie searchByName(String name) throws MovieNotFoundException;

	void removeById(int id) throws MovieNotFoundException;

	void removeByName(String name) throws MovieNotFoundException;
}
