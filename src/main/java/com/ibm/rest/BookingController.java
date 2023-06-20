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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Booking;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.service.BookingService;

/**
 * REST controller for handling Booking-related operations.
 */
@CrossOrigin
@RestController
public class BookingController {

	@Autowired
	private BookingService service;

	/**
	 * Endpoint for adding a new booking.
	 *
	 * Example URL: http://localhost:8880/booking/add
	 * 
	 * @param b the booking object to be added
	 * @return ResponseEntity containing the added booking and HTTP status
	 */
	@PostMapping(path = "/booking/book", consumes = "application/json")
	public ResponseEntity<Booking> addBooking(@RequestBody Booking b) {
		service.save(b);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController").body(b);
	}

	/**
	 * Endpoint for retrieving all bookings.
	 *
	 * Example URL: http://localhost:8880/admin/booking/all
	 * 
	 * @return ResponseEntity containing the list of all bookings and HTTP status
	 */
	@GetMapping(path = "/admin/booking/all", produces = "application/json")
	public ResponseEntity<List<Booking>> getAllBookings() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body(service.list());
	}

	/**
	 * Endpoint for retrieving a booking by ID.
	 *
	 * Example URL: http://localhost:8880/booking/search/id/{id}
	 * 
	 * @param id the ID of the booking
	 * @return ResponseEntity containing the booking object and HTTP status
	 * @throws BookingNotFoundException if the booking is not found
	 */
	@GetMapping(path = "/booking/search/id/{id}", produces = "application/json")
	public ResponseEntity<Booking> getBookingById(@PathVariable int id) throws BookingNotFoundException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body(service.searchById(id));
	}

	/**
	 * Endpoint for removing a booking by ID.
	 *
	 * Example URL: http://localhost:8880/admin/booking/remove/id/{id}
	 * 
	 * @param id the ID of the booking to be removed
	 * @return ResponseEntity containing the response message and HTTP status
	 * @throws BookingNotFoundException if the booking is not found
	 */
	@DeleteMapping(path = "/admin/booking/remove/id/{id}", produces = "application/json")
	public ResponseEntity<String> removeBookingById(@PathVariable int id) throws BookingNotFoundException {
		service.remove(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "BookingController")
				.body("Booking with id: " + id + " deleted successfully");
	}

}
