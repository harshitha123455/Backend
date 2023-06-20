package com.ibm.service;

import java.util.List;

import com.ibm.entity.Screen;
import com.ibm.exception.ScreenAlreadyExistException;
import com.ibm.exception.ScreenNotFoundException;

/**
 * Service interface for managing screens.
 */
public interface ScreenService {

    /**
     * Saves a screen.
     *
     * @param screen the screen to be saved
     * @return the ID of the saved screen
     * @throws ScreenAlreadyExistException if the screen already exists
     */
    int save(Screen screen) throws ScreenAlreadyExistException;

    /**
     * Updates a screen.
     *
     * @param screen the screen to be updated
     * @return the ID of the updated screen
     * @throws ScreenNotFoundException     if the screen is not found
     * @throws ScreenAlreadyExistException if the updated screen conflicts with an existing screen
     */
    int update(Screen screen) throws ScreenNotFoundException, ScreenAlreadyExistException;

    /**
     * Retrieves a list of all screens.
     *
     * @return a list of Screen entities
     */
    List<Screen> list();

    /**
     * Searches for a screen by ID.
     *
     * @param id the ID of the screen to search for
     * @return the found Screen entity
     * @throws ScreenNotFoundException if the screen is not found
     */
    Screen searchById(int id) throws ScreenNotFoundException;

    /**
     * Searches for a screen by name.
     *
     * @param name the name of the screen to search for
     * @return the found Screen entity
     * @throws ScreenNotFoundException if the screen is not found
     */
    Screen searchByName(String name) throws ScreenNotFoundException;

    /**
     * Removes a screen by ID.
     *
     * @param id the ID of the screen to remove
     * @throws ScreenNotFoundException if the screen is not found
     */
    void removeById(int id) throws ScreenNotFoundException;

    /**
     * Removes a screen by name.
     *
     * @param name the name of the screen to remove
     * @throws ScreenNotFoundException if the screen is not found
     */
    void removeByName(String name) throws ScreenNotFoundException;
}
