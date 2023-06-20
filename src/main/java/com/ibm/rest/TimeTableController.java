package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.TimeTable;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.TimeTableRequest;
import com.ibm.service.TimeTableService;

/**
 * REST controller for handling Time table-related operations.
 */
@RestController
@CrossOrigin
public class TimeTableController {

	@Autowired
	private TimeTableService service;

	/**
	 * End point for adding a new timetable.
	 * 
	 * Example URL: http://localhost:8880/admin/timeTable/add
	 *
	 * @param t the timetable object to add
	 * @return ResponseEntity with a success message and the ID of the added
	 *         timetable
	 * @throws TimeTableAlreadyExistException if the timetable already exists
	 */
	@PostMapping(path = "/admin/timeTable/add", consumes = "application/json")
	public ResponseEntity<String> addTimeTable(@RequestBody TimeTable t) throws TimeTableAlreadyExistException {
		int id = service.save(t);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable added with id: " + id);
	}

	/**
	 * End point for updating an existing timetable.
	 * 
	 * Example URL: http://localhost:8880/admin/timeTable/update
	 *
	 * @param t the updated timetable object
	 * @return ResponseEntity with a success message and the ID of the updated
	 *         timetable
	 * @throws TimeTableNotFoundException if the timetable is not found
	 */
	@PutMapping(path = "/admin/timeTable/update", consumes = "application/json")
	public ResponseEntity<String> updateTimeTable(@RequestBody TimeTable t) throws TimeTableNotFoundException {
		int id = service.update(t);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable with id: " + id + " updated");
	}

	/**
	 * End point for retrieving all timetables.
	 * 
	 * Example URL: http://localhost:8880/timeTable/all
	 *
	 * @return ResponseEntity containing the list of timetables
	 */
	@GetMapping(path = "/timeTable/all", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTable() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.list());
	}

	/**
	 * End point for retrieving all timetables by screen ID.
	 * 
	 * Example URL: http://localhost:8880/timeTable/search/screen/id/{id}
	 *
	 * @param id the ID of the screen
	 * @return ResponseEntity containing the list of timetables for the screen
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@GetMapping(path = "/timeTable/search/screen/id/{id}", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTableByScreenId(@PathVariable int id)
			throws ScreenNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.listByScreenId(id));
	}

	/**
	 * End point for retrieving a timetable by date and screen.
	 * 
	 * Example URL: http://localhost:8880/search/dateAndScreen
	 *
	 * @param ttr the TimeTableRequest object containing date and screen ID
	 * @return ResponseEntity containing the matching timetable
	 * @throws ScreenNotFoundException    if the screen is not found
	 * @throws TimeTableNotFoundException if the timetable is not found
	 */
	@PostMapping(path = "/timeTable/search/dateAndScreen", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TimeTable> getTimeTableByDateAndScreenId(@RequestBody TimeTableRequest ttr)
			throws ScreenNotFoundException, TimeTableNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchByDateAndScreen(ttr));
	}

	/**
	 * End point for retrieving all timetables by screen name.
	 * 
	 * Example URL: http://localhost:8880/timeTable/search/screen/name/{name}
	 *
	 * @param name the name of the screen
	 * @return ResponseEntity containing the list of timetables for the screen
	 * @throws ScreenNotFoundException if the screen is not found
	 */
	@GetMapping(path = "/timeTable/search/screen/name/{name}", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTableByScreenName(@PathVariable String name)
			throws ScreenNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.listByScreenName(name));
	}

	/**
	 * End point for retrieving a timetable by ID.
	 * 
	 * Example URL: http://localhost:8880/timeTable/search/id/{id}
	 *
	 * @param id the ID of the timetable
	 * @return ResponseEntity containing the matching timetable
	 * @throws TimeTableNotFoundException if the timetable is not found
	 */
	@GetMapping(path = "/timeTable/search/id/{id}", produces = "application/json")
	public ResponseEntity<TimeTable> getTimeTable(@PathVariable int id) throws TimeTableNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchById(id));
	}

	/**
	 * End point for retrieving a timetable by show ID.
	 * 
	 * Example URL: http://localhost:8880/timeTable/search/show/id/{id}
	 *
	 * @param id the ID of the show
	 * @return ResponseEntity containing the matching timetable
	 */
	@GetMapping(path = "/timeTable/search/show/id/{id}", produces = "application/json")
	public ResponseEntity<TimeTable> getTimeTableByShowId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchByShow(id));
	}

	/**
	 * End point for removing a timetable by ID.
	 * 
	 * Example URL: http://localhost:8880/admin/timeTable/remove/id/{id}
	 *
	 * @param id the ID of the timetable to remove
	 * @return ResponseEntity with a success message
	 */
	@DeleteMapping(path = "/admin/timeTable/remove/id/{id}")
	public ResponseEntity<String> removetimeTableById(@PathVariable int id) {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable with id: " + id + " deleted successfully");
	}
}
