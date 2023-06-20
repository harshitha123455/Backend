package com.ibm.service;

import java.util.List;

import com.ibm.entity.Booking;
import com.ibm.exception.BookingNotFoundException;

/**
 * The BookingService interface provides methods for managing bookings.
 */
public interface BookingService {

	/**
	 * Constant representing the start index of normal bookings.
	 */
	int normalStart = 1;

	/**
	 * Constant representing the end index of normal bookings.
	 */
	int normalEnd = 60;

	/**
	 * Constant representing the start index of executive bookings.
	 */
	int executiveStart = 61;

	/**
	 * Constant representing the end index of executive bookings.
	 */
	int executiveEnd = 80;

	/**
	 * Constant representing the start index of premium bookings.
	 */
	int premiumStart = 81;

	/**
	 * Constant representing the end index of premium bookings.
	 */
	int premiumEnd = 100;

	/**
	 * Saves a booking.
	 *
	 * @param b the booking to be saved
	 * @return the ID of the saved booking
	 */
	Booking save(Booking b);

	/**
	 * Retrieves a list of all bookings.
	 *
	 * @return the list of all bookings
	 */
	List<Booking> list();

	/**
	 * Retrieves a booking by its ID.
	 *
	 * @param id the ID of the booking to retrieve
	 * @return the booking with the specified ID
	 * @throws BookingNotFoundException if the booking is not found
	 */
	Booking searchById(int id) throws BookingNotFoundException;

	/**
	 * Retrieves a list of bookings by the ID of the show.
	 *
	 * @param id the ID of the show
	 * @return the list of bookings for the specified show
	 */
	List<Booking> searchByShowId(int id);

	/**
	 * Removes a booking by its ID.
	 *
	 * @param id the ID of the booking to remove
	 * @throws BookingNotFoundException if the booking is not found
	 */
	void remove(int id) throws BookingNotFoundException;
}
