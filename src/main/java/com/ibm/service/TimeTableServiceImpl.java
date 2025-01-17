package com.ibm.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.entity.TimeTable;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.TimeTableRequest;
import com.ibm.repo.TimeTableRepository;

/**
 * Implementation of the {@link TimeTableService} interface that manages movie
 * time tables.
 */
@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private TimeTableRepository repo;

	@Autowired
	private ScreenService screenService;

	@Autowired
	private ShowService showService;

	/**
	 * Saves a new time table.
	 *
	 * @param t the time table to save
	 * @return the ID of the saved time table
	 * @throws TimeTableAlreadyExistException if a time table already exists for the
	 *                                        given screen and date
	 */
	@Override
	public int save(TimeTable t) throws TimeTableAlreadyExistException {
		if (repo.findByDateAndScreen(t.getDate(), t.getScreen()) != null)
			throw new TimeTableAlreadyExistException(
					"Timetable for " + t.getScreen().getName() + " already exists on " + t.getDate());

		SeatingArrangement sa = new SeatingArrangement();
		Screen s = t.getScreen();
		sa.setTotalSeats(s.getTotalSeats());
		sa.setAvailableSeats(sa.getTotalSeats());
		sa.setAvailableNormalSeats(s.getNormalSeats());
		sa.setAvailablePremiumSeats(s.getPremiumSeats());
		sa.setAvailableExecutiveSeats(s.getExecutiveSeats());
		sa.setReserved(new ArrayList<>(Collections.nCopies(100, false)));
		t.getSlot1().setSeatingArrangement(sa);
		t.getSlot2().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot3().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot4().setSeatingArrangement(new SeatingArrangement(sa));

		t.getSlot1().setTime("Morning Show");
		t.getSlot2().setTime("Noon Show");
		t.getSlot3().setTime("Evening Show");
		t.getSlot4().setTime("Night Show");

		repo.save(t);
		return t.getId();
	}

	/**
	 * Updates an existing time table.
	 *
	 * @param t the time table to update
	 * @return the ID of the updated time table
	 * @throws TimeTableNotFoundException if the specified time table does not exist
	 */
	@Override
	public int update(TimeTable t) throws TimeTableNotFoundException {
		searchById(t.getId()); // check whether the timetable exists
		repo.save(t);
		return t.getId();
	}

	/**
	 * Retrieves a list of all time tables.
	 *
	 * @return the list of time tables
	 */
	@Override
	public List<TimeTable> list() {
		return repo.findAll();
	}

	/**
	 * Retrieves a list of time tables based on the screen ID.
	 *
	 * @param id the ID of the screen
	 * @return the list of time tables for the specified screen
	 * @throws ScreenNotFoundException if the screen with the specified ID does not
	 *                                 exist
	 */
	@Override
	public List<TimeTable> listByScreenId(int id) throws ScreenNotFoundException {
		return repo.findAllByScreen(screenService.searchById(id));
	}

	/**
	 * Retrieves a list of time tables based on the screen name.
	 *
	 * @param name the name of the screen
	 * @return the list of time tables for the specified screen
	 * @throws ScreenNotFoundException if the screen with the specified name does
	 *                                 not exist
	 */
	@Override
	public List<TimeTable> listByScreenName(String name) throws ScreenNotFoundException {
		return repo.findAllByScreen(screenService.searchByName(name));
	}

	/**
	 * Retrieves a list of time tables based on the date.
	 *
	 * @param d the date
	 * @return the list of time tables for the specified date
	 */
	@Override
	public List<TimeTable> listByDate(Date d) {
		return repo.findAllByDate(d);
	}

	/**
	 * Removes a time table by ID.
	 *
	 * @param id the ID of the time table to remove
	 * @return {@code true} if the time table was successfully removed,
	 *         {@code false} otherwise
	 */
	@Override
	public Boolean removeById(int id) {
		repo.deleteById(id);
		return true;
	}

	/**
	 * Searches for a time table by ID.
	 *
	 * @param id the ID of the time table to search for
	 * @return the found time table
	 * @throws TimeTableNotFoundException if the specified time table does not exist
	 */
	@Override
	public TimeTable searchById(int id) throws TimeTableNotFoundException {
		TimeTable t;
		try {
			t = repo.findById(id).get();
		} catch (Exception e) {
			throw new TimeTableNotFoundException(id);
		}
		return t;
	}

	/**
	 * Searches for a time table by date and screen.
	 *
	 * @param ttr the time table request containing the date and screen ID
	 * @return the found time table
	 * @throws ScreenNotFoundException    if the screen with the specified ID does
	 *                                    not exist
	 * @throws TimeTableNotFoundException if no time table is found for the given
	 *                                    date and screen
	 */
	@Override
	public TimeTable searchByDateAndScreen(TimeTableRequest ttr)
			throws ScreenNotFoundException, TimeTableNotFoundException {
		TimeTable t = repo.findByDateAndScreen(ttr.getDate(), screenService.searchById(ttr.getSid()));
		if (t == null)
			throw new TimeTableNotFoundException("No Timetable found for screen: "
					+ screenService.searchById(ttr.getSid()).getName() + " on " + ttr.getDate());
		return t;
	}

	/**
	 * Searches for a time table by show ID.
	 *
	 * @param id the ID of the show
	 * @return the found time table
	 */
	@Override
	public TimeTable searchByShow(int id) {
		Shows s = showService.searchById(id);
		return repo.findBySlot1OrSlot2OrSlot3OrSlot4(s, s, s, s);
	}

}
