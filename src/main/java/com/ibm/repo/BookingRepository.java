package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Booking;
import com.ibm.entity.Shows;

/**
 * Repository interface for managing the Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	/**
	 * Retrieves a list of Booking entities by the specified Shows.
	 *
	 * @param s The Shows entity to filter the bookings.
	 * @return A list of Booking entities associated with the specified Shows.
	 */
	List<Booking> findAllByShows(Shows s);
}
