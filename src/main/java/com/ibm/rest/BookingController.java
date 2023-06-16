package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Booking;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.service.BookingService;

@CrossOrigin
@RestController
public class BookingController {

	@Autowired
	private BookingService service;
	
//	http://localhost:8880/booking/add
	@PostMapping(path = "/booking/add", consumes="application/json")
	public ResponseEntity<String> addBooking(@RequestBody Booking b){
		int id = service.save(b);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body("Booking added with id: " + id);
	}

//	http://localhost:8880/admin/booking/all
	@GetMapping(path = "/admin/booking/all", produces="application/json")
	public ResponseEntity<List<Booking>> getAllBookings(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body(service.list());
	}
	
//	http://localhost:8880/booking/search/id/{id}
	@GetMapping(path = "/booking/search/id/{id}", produces="application/json")
	public ResponseEntity<Booking> getBookingbyId(@PathVariable int id) throws BookingNotFoundException{
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body(service.searchById(id));
	}
	
//	http://localhost:8880/admin/booking/remove/id/{id}
	@GetMapping(path = "/admin/booking/search/id/{id}", produces="application/json")
	public ResponseEntity<String> removeBookingbyId(@PathVariable int id) throws BookingNotFoundException{
		service.remove(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body("Booking with id: " + id + " deleted successfully");
	}
	
}
