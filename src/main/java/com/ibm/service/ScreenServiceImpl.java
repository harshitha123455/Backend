package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.entity.Screen;
import com.ibm.repo.ScreenRepository;

@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository repo;

	@Override
	public int save(Screen s) throws ScreenAlreadyExistException {
		Screen s1 = repo.findByName(s.getName());
		if (s1 != null)
			throw new ScreenAlreadyExistException(s1.getName());
		repo.save(s);
		return s.getId();
	}

	@Override
	public List<Screen> list() {
		return repo.findAll();
	}

	@Override
	public Screen searchById(int id) throws ScreenNotFoundException {
		Screen s;
		try {
			s = repo.findById(id).get();
		} catch (Exception e) {
			throw new ScreenNotFoundException(id);
		}
		return s;
	}

	@Override
	public Screen searchByName(String name) throws ScreenNotFoundException {
		Screen s = repo.findByName(name);
		if (s == null)
			throw new ScreenNotFoundException(name);
		return s;
	}

	@Override
	public void removeById(int id) throws ScreenNotFoundException {
		Screen s;
		try {
			s = repo.findById(id).get();
		} catch (Exception e) {
			throw new ScreenNotFoundException(id);
		}
		repo.delete(s);
	}

	@Override
	public void removeByName(String name) throws ScreenNotFoundException {
		Screen s = repo.findByName(name);
		if (s == null)
			throw new ScreenNotFoundException(name);
		repo.delete(s);
	}

}
