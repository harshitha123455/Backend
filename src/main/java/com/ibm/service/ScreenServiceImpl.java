package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.repo.ScreenRepository;

/**
 * Implementation of the {@link ScreenService} interface that manages screens.
 */
@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository repo;

	/**
	 * Saves a screen.
	 *
	 * @param screen the screen to be saved
	 * @return the ID of the saved screen
	 * @throws ScreenAlreadyExistException if the screen already exists
	 */
	@Override
	public int save(Screen screen) throws ScreenAlreadyExistException {
		Screen existingScreen = repo.findByName(screen.getName());
		if (existingScreen != null && existingScreen.getId() != screen.getId()) {
			throw new ScreenAlreadyExistException(existingScreen.getName());
		}
		repo.save(screen);
		return screen.getId();
	}

	/**
	 * Updates a screen.
	 *
	 * @param screen the screen to be updated
	 * @return the ID of the updated screen
	 * @throws ScreenNotFoundException     if the screen is not found
	 * @throws ScreenAlreadyExistException if the updated screen conflicts with an
	 *                                     existing screen
	 */
	@Override
	public int update(Screen screen) throws ScreenNotFoundException, ScreenAlreadyExistException {
		searchById(screen.getId()); // check whether the screen exists
		save(screen);
		return screen.getId();
	}

	/**
	 * Retrieves a list of all screens.
	 *
	 * @return a list of Screen entities
	 */
	@Override
	public List<Screen> list() {
		return repo.findAll();
	}

	/**
	 * Searches for a screen by ID.
	 *
	 * @param id the ID of the screen to search for
	 * @return the found Screen entity
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@Override
	public Screen searchById(int id) throws ScreenNotFoundException {
		Screen screen;
		try {
			screen = repo.findById(id).get();
		} catch (Exception e) {
			throw new ScreenNotFoundException(id);
		}
		return screen;
	}

	/**
	 * Searches for a screen by name.
	 *
	 * @param name the name of the screen to search for
	 * @return the found Screen entity
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@Override
	public Screen searchByName(String name) throws ScreenNotFoundException {
		Screen screen = repo.findByName(name);
		if (screen == null)
			throw new ScreenNotFoundException(name);
		return screen;
	}

	/**
	 * Removes a screen by ID.
	 *
	 * @param id the ID of the screen to remove
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@Override
	public void removeById(int id) throws ScreenNotFoundException {
		Screen screen;
		try {
			screen = repo.findById(id).get();
		} catch (Exception e) {
			throw new ScreenNotFoundException(id);
		}
		repo.delete(screen);
	}

	/**
	 * Removes a screen by name.
	 *
	 * @param name the name of the screen to remove
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@Override
	public void removeByName(String name) throws ScreenNotFoundException {
		Screen screen = repo.findByName(name);
		if (screen == null)
			throw new ScreenNotFoundException(name);
		repo.delete(screen);
	}
}
