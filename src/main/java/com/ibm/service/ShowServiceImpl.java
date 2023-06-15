package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.SeatingArrangementRepository;
import com.ibm.repo.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowRepository repo;
	
	@Autowired
	private MovieService service;

	@Override
	public int save(Shows s) {
		repo.save(s);
		return s.getId();
	}

	@Override
	public List<Shows> list() {
		return repo.findAll();
	}

	@Override
	public Shows searchById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Shows> searchByMovieId(int id) throws MovieNotFoundException {
		 return repo.findAllByMovie(service.searchById(id));
	}
	
	@Override
	public List<Shows> searchByMovieName(String name) throws MovieNotFoundException {
		return repo.findAllByMovie(service.searchByName(name));
	}

	@Override
	public void removeById(int id) {
		repo.deleteById(id);
	}

}
