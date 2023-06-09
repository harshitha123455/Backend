package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.repo.SeatingArrangementRepository;
import com.ibm.repo.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowRepository showRepo;

	@Override
	public int save(Shows s) {
		showRepo.save(s);
		return s.getId();
	}

	@Override
	public List<Shows> list() {
		return showRepo.findAll();
	}

	@Override
	public Shows searchById(int id) {
		return showRepo.findById(id).get();
	}

	@Override
	public List<Shows> searchByMovie(Movie m) {
		return showRepo.findAllByMovie(m);
	}

	@Override
	public void removeById(int id) {
		showRepo.deleteById(id);
	}

}
