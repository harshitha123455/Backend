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

@CrossOrigin
@RestController
public class TimeTableController {

	@Autowired
	private TimeTableService service;

//	http://localhost:8880/admin/timeTable/add
	@PostMapping(path = "/admin/timeTable/add", consumes = "application/json")
	public ResponseEntity<String> addTimeTable(@RequestBody TimeTable t) throws TimeTableAlreadyExistException {
		int id = service.save(t);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable added with id: " + id);
	}

//	http://localhost:8880/admin/timeTable/update
	@PutMapping(path = "/admin/timeTable/update", consumes = "application/json")
	public ResponseEntity<String> updateTimeTable(@RequestBody TimeTable t) throws TimeTableNotFoundException {
		int id = service.update(t);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable with id: " + id + " updated");
	}

//	http://localhost:8880/timeTable/all
	@GetMapping(path = "/timeTable/all", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTable() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.list());
	}

//	http://localhost:8880/timeTable/search/screen/id/{id}
	@GetMapping(path = "/timeTable/search/screen/id/{id}", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTableByScreenId(@PathVariable int id) throws ScreenNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.listByScreenId(id));
	}
//	http://localhost:8880/timeTable/search/dateAndScreen
	@PostMapping(path = "/timeTable/search/dateAndScreen", consumes="application/json", produces = "application/json")
	public ResponseEntity<TimeTable> getTimeTableByDateAndScreenId(@RequestBody TimeTableRequest ttr) throws ScreenNotFoundException, TimeTableNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchByDateAndScreen(ttr));
	}
	
//	http://localhost:8880/timeTable/search/screen/name/{name}
	@GetMapping(path = "/timeTable/search/screen/name/{name}", produces = "application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTableByScreenName(@PathVariable String name) throws ScreenNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.listByScreenName(name));
	}

//	http://localhost:8880/timeTable/search/id/{id}
	@GetMapping(path = "/timeTable/search/id/{id}", produces = "application/json")
	public ResponseEntity<TimeTable> getTimeTable(@PathVariable int id) throws TimeTableNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchById(id));
	}
	
//	http://localhost:8880/timeTable/search/show/id/{id}
	@GetMapping(path="/timeTable/search/show/id/{id}", produces="application/json")
	public ResponseEntity<TimeTable> getTimeTableByShowId(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchByShow(id));
	}

//	http://localhost:8880/admin/timeTable/remove/id/{id}
	@DeleteMapping(path = "/admin/timeTable/remove/id/{id}")
	public ResponseEntity<String> removetimeTableById(@PathVariable int id) {
		service.removeById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable with id: " + id + " deleted successfully");
	}
}
