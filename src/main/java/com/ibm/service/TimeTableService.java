package com.ibm.service;

import java.sql.Date;
import java.util.List;

import com.ibm.entity.TimeTable;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.TimeTableRequest;

/**
 * Service interface for managing movie time tables.
 */
public interface TimeTableService {

	/**
	 * Saves a movie time table.
	 *
	 * @param t the time table to be saved
	 * @return the ID of the saved time table
	 * @throws TimeTableAlreadyExistException if a time table with the same ID
	 *                                        already exists
	 */
	int save(TimeTable t) throws TimeTableAlreadyExistException;

	/**
	 * Updates a movie time table.
	 *
	 * @param t the time table to be updated
	 * @return the ID of the updated time table
	 * @throws TimeTableNotFoundException if the time table with the given ID is not
	 *                                    found
	 */
	int update(TimeTable t) throws TimeTableNotFoundException;

	/**
	 * Searches for a movie time table by ID.
	 *
	 * @param id the ID of the time table to search for
	 * @return the found TimeTable entity
	 * @throws TimeTableNotFoundException if the time table with the given ID is not
	 *                                    found
	 */
	TimeTable searchById(int id) throws TimeTableNotFoundException;

	/**
	 * Searches for a movie time table by date and screen.
	 *
	 * @param ttr the TimeTableRequest object containing the date and screen
	 *            information
	 * @return the found TimeTable entity
	 * @throws ScreenNotFoundException    if the screen with the given ID is not
	 *                                    found
	 * @throws TimeTableNotFoundException if the time table for the given date and
	 *                                    screen is not found
	 */
	TimeTable searchByDateAndScreen(TimeTableRequest ttr) throws ScreenNotFoundException, TimeTableNotFoundException;

	/**
	 * Searches for a movie time table by show ID.
	 *
	 * @param id the ID of the show to search for
	 * @return the found TimeTable entity
	 */
	TimeTable searchByShow(int id);

	/**
	 * Retrieves a list of all movie time tables.
	 *
	 * @return a list of TimeTable entities
	 */
	List<TimeTable> list();

	/**
	 * Retrieves a list of movie time tables for a given screen ID.
	 *
	 * @param id the ID of the screen to search for time tables
	 * @return a list of TimeTable entities associated with the given screen ID
	 * @throws ScreenNotFoundException if the screen with the given ID is not found
	 */
	List<TimeTable> listByScreenId(int id) throws ScreenNotFoundException;

	/**
	 * Retrieves a list of movie time tables for a given screen name.
	 *
	 * @param name the name of the screen to search for time tables
	 * @return a list of TimeTable entities associated with the given screen name
	 * @throws ScreenNotFoundException if the screen with the given name is not
	 *                                 found
	 */
	List<TimeTable> listByScreenName(String name) throws ScreenNotFoundException;

	/**
	 * Retrieves a list of movie time tables for a given date.
	 *
	 * @param d the date to search for time tables
	 * @return a list of TimeTable entities associated with the given date
	 */
	List<TimeTable> listByDate(Date d);

	/**
	 * Removes a movie time table by ID.
	 *
	 * @param id the ID of the time table to remove
	 * @return true if the time table was successfully removed, false otherwise
	 */
	Boolean removeById(int id);

}
