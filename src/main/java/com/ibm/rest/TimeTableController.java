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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Screen;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.TimeTable;
import com.ibm.service.TimeTableService;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin")
public class TimeTableController {

	@Autowired
	private TimeTableService service;
//	http://localhost:8880/admiin/timeTable/add
	@PostMapping(path="/timeTable/add", consumes="application/json")
	public ResponseEntity<String> addTimeTable(@RequestBody TimeTable t){
		SeatingArrangement sa = new SeatingArrangement();
		Screen s = t.getScreen();
		sa.setTotalSeats(s.getTotalSeats());
		sa.setAvailableSeats(sa.getTotalSeats());
		sa.setAvailableNormalSeats(s.getNormalSeats());
		sa.setAvailablePremiumSeats(s.getPremiumSeats());
		sa.setAvailableExecutiveSeats(s.getExecutiveSeats());
		sa.setReserved(new Boolean[s.getTotalSeats()]);
		t.getSlot1().setSeatingArrangement(sa);
		t.getSlot2().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot3().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot4().setSeatingArrangement(new SeatingArrangement(sa));
		int id = service.save(t);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable added with id: " + id);
	}
	
//	http://localhost:8880/admin/timeTable/all
	@GetMapping(path="/timeTable/all", produces="application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTable(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.list());
	}
	
//	http://localhost:8880/admin/timeTable/search/screen
	@PostMapping(path="/timeTable/search/screen", produces="application/json")
	public ResponseEntity<List<TimeTable>> getAllTimeTableByScreen(@RequestBody Screen s){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.listByScreen(s));
	}
	
//	http://localhost:8880/admin/timeTable/search/id/{id}
	@GetMapping(path="/timeTable/search/id/{id}", produces="application/json")
	public ResponseEntity<TimeTable> getTimeTable(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body(service.searchById(id));
	}
	
//	http://localhost:8880/admin/timeTable/remove/id/{id}
	@DeleteMapping(path = "/timeTable/remove/id/{id}")
	public ResponseEntity<String> removetimeTableById(@PathVariable int id){
		service.removeById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "TimeTableController")
				.body("TimeTable with id: " + id + " deleted successfully");
	}
}
