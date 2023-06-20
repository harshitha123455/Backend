package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Screen;

/**
 * Repository interface for managing the Screen entity.
 */
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

	/**
	 * Retrieves a screen by its name.
	 *
	 * @param name the name of the screen
	 * @return the screen with the specified name
	 */
	Screen findByName(String name);
}
