package com.ibm.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Booking;
import com.ibm.entity.Payment;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.repo.BookingRepository;
import com.ibm.repo.SeatingArrangementRepository;
import com.ibm.repo.ShowRepository;

/**
 * Implementation of the {@link BookingService} interface that provides methods
 * for managing bookings.
 */
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private ShowRepository showsRepo;

	@Autowired
	private SeatingArrangementRepository seatRepo;

	@Autowired
	private ShowService service;

	@Autowired
	private EmailSender email;

	/**
	 * Saves the provided booking and performs necessary updates to the seating
	 * arrangement and payment details.
	 *
	 * @param b The booking to be saved.
	 * @return The saved booking.
	 */
	@Override
	public Booking save(Booking b) {
		Shows s = b.getShows();
		SeatingArrangement sa = s.getSeatingArrangement();
		List<Boolean> reserved = sa.getReserved();
		List<String> types = new ArrayList<>();
		for (Integer num : b.getPos()) {
			reserved.set(num - 1, true);
			if (num >= normalStart && num <= normalEnd) {
				sa.setAvailableNormalSeats(sa.getAvailableNormalSeats() - 1);
				types.add("Normal");
			} else if (num >= executiveStart && num <= executiveEnd) {
				sa.setAvailableExecutiveSeats(sa.getAvailableExecutiveSeats() - 1);
				types.add("Executive");
			} else {
				sa.setAvailablePremiumSeats(sa.getAvailablePremiumSeats() - 1);
				types.add("Premium");
			}
			sa.setAvailableSeats(sa.getAvailableSeats() - 1);
		}
		s.setSeatingArrangement(sa);
		b.setShows(s);
		b.setTypes(types);
		Shows updatedShows = showsRepo.save(b.getShows());
		b.setShows(updatedShows);
		Payment p = new Payment();
		p.setAmount(b.getAmount());
		p.setTransactionId(generateRandomCode());
		b.setPayment(p);
		Booking savedBooking = bookingRepo.save(b);

		// Update seating arrangement in the database
		SeatingArrangement savedSeatingArrangement = seatRepo.save(sa);
		savedBooking.getShows().setSeatingArrangement(savedSeatingArrangement);

		// Send email
		email.sendMyEmail(b);

		return savedBooking;
	}

	/**
	 * Retrieves a list of all bookings.
	 *
	 * @return A list of all bookings.
	 */
	@Override
	public List<Booking> list() {
		return bookingRepo.findAll();
	}

	/**
	 * Searches for a booking by its ID.
	 *
	 * @param id The ID of the booking to search for.
	 * @return The found booking.
	 * @throws BookingNotFoundException If the booking with the given ID is not
	 *                                  found.
	 */
	@Override
	public Booking searchById(int id) throws BookingNotFoundException {
		Booking b;
		try {
			b = bookingRepo.findById(id).get();
		} catch (Exception e) {
			throw new BookingNotFoundException(id);
		}
		return b;
	}

	/**
	 * Searches for bookings associated with a specific show.
	 *
	 * @param id The ID of the show to search for.
	 * @return A list of bookings associated with the show.
	 */
	@Override
	public List<Booking> searchByShowId(int id) {
		Shows s = service.searchById(id);
		return bookingRepo.findAllByShows(s);
	}

	/**
	 * Removes a booking with the given ID and updates the seating arrangement
	 * accordingly.
	 *
	 * @param id The ID of the booking to remove.
	 * @throws BookingNotFoundException If the booking with the given ID is not
	 *                                  found.
	 */
	@Override
	public void remove(int id) throws BookingNotFoundException {
		Booking b;
		try {
			b = bookingRepo.findById(id).get();
		} catch (Exception e) {
			throw new BookingNotFoundException(id);
		}
		SeatingArrangement sa = b.getShows().getSeatingArrangement();
		List<Boolean> reserved = sa.getReserved();
		for (Integer num : b.getPos()) {
			reserved.set(num, true);
			if (num >= normalStart && num <= normalEnd)
				sa.setAvailableNormalSeats(sa.getAvailableNormalSeats() + 1);
			else if (num >= executiveStart && num <= executiveEnd)
				sa.setAvailableExecutiveSeats(sa.getAvailableExecutiveSeats() + 1);
			else
				sa.setAvailablePremiumSeats(sa.getAvailablePremiumSeats() + 1);
			sa.setAvailableSeats(sa.getAvailableSeats() + 1);
		}
		bookingRepo.deleteById(id);
	}

	/**
	 * Generates a random transaction ID based on the current timestamp.
	 *
	 * @return The randomly generated transaction ID.
	 */
	private static String generateRandomCode() {
		long timestamp = Instant.now().toEpochMilli();
		Random random = new Random();
		int randomNumber = random.nextInt(100000);
		String code = String.format("%d%06d", timestamp, randomNumber);

		return code;
	}
}
