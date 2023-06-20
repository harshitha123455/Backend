package com.ibm.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Screen;
import com.ibm.entity.Shows;
import com.ibm.entity.TimeTable;

/**
 * Repository interface for managing the TimeTable entity.
 */
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {

	/**
	 * Retrieves a list of timetables by the specified screen.
	 *
	 * @param screen The screen object
	 * @return A list of timetables associated with the screen
	 */
	List<TimeTable> findAllByScreen(Screen screen);

	/**
	 * Retrieves a list of timetables by the specified date.
	 *
	 * @param date The date object
	 * @return A list of timetables associated with the date
	 */
	List<TimeTable> findAllByDate(Date date);

	/**
	 * Retrieves a timetable by the specified date and screen.
	 *
	 * @param date   The date object
	 * @param screen The screen object
	 * @return The timetable associated with the date and screen
	 */
	TimeTable findByDateAndScreen(Date date, Screen screen);

	/**
	 * Retrieves a timetable by the specified shows. The shows can be one of the
	 * four slots in the timetable.
	 *
	 * @param s1 The first shows object
	 * @param s2 The second shows object
	 * @param s3 The third shows object
	 * @param s4 The fourth shows object
	 * @return The timetable containing one of the specified shows
	 */
	TimeTable findBySlot1OrSlot2OrSlot3OrSlot4(Shows s1, Shows s2, Shows s3, Shows s4);
}
