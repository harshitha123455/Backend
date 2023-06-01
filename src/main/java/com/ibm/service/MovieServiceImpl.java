package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.repo.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repo;

	@Override
	public int save(Movie m) throws MovieAlreadyExistException {
		Movie m1 = repo.findByName(m.getName());
		if (m1 != null)
			throw new MovieAlreadyExistException(m1.getName());
		repo.save(m);
		return m.getId();
	}

	@Override
	public List<Movie> list() {
		return repo.findAll();
	}

	@Override
	public Movie searchById(int id) throws MovieNotFoundException {
		Movie m;
		try {
			m = repo.findById(id).get();
		} catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
		return m;
	}

	@Override
	public Movie searchByName(String name) throws MovieNotFoundException {
		Movie m = repo.findByName(name);
		if (m == null)
			throw new MovieNotFoundException(name);
		return m;
	}

	@Override
	public void removeById(int id) throws MovieNotFoundException {
		Movie m;
		try {
			m = repo.findById(id).get();
		} catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
		repo.delete(m);
	}

	@Override
	public void removeByName(String name) throws MovieNotFoundException {
		Movie m = repo.findByName(name);
		if (m == null)
			throw new MovieNotFoundException(name);
		else
			repo.delete(m);

	}

}
