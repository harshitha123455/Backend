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
import com.ibm.entity.User;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.repo.BookingRepository;
import com.ibm.repo.PaymentRepository;
import com.ibm.repo.SeatingArrangementRepository;
import com.ibm.repo.ShowRepository;
import com.ibm.repo.UserRepository;

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
	private MyEmailSender email;
	
	@Autowired
	private EmailService es;

	@Override
	public Booking save(Booking b) {
	    Shows s = b.getShows();
	    SeatingArrangement sa = s.getSeatingArrangement();
	    Boolean[] reserved = sa.getReserved();
	    List<String> types = new ArrayList<>();
	    for (Integer num : b.getPos()) {
	        System.err.println(num);
	        reserved[num-1] = true;
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
	    b.setType(types);
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
//	    es.sendEmail(b.getUser().getEmail(), "Hello" , "Done");
	    
	    return savedBooking;
	}



	@Override
	public List<Booking> list() {
		return bookingRepo.findAll();
	}

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

	@Override
	public List<Booking> searchByShowId(int id) {
		Shows s = service.searchById(id);
		return bookingRepo.findAllByShows(s);
	}

	@Override
	public void remove(int id) throws BookingNotFoundException {
		Booking b;
		try {
			b = bookingRepo.findById(id).get();
		} catch (Exception e) {
			throw new BookingNotFoundException(id);
		}
		SeatingArrangement sa = b.getShows().getSeatingArrangement();
		Boolean[] reserved = sa.getReserved();
		for (Integer num : b.getPos()) {
			reserved[num] = true;
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
	
	private static String generateRandomCode() {
        // Get the current timestamp
        long timestamp = Instant.now().toEpochMilli();

        // Create a random number generator
        Random random = new Random();

        // Generate a random number
        int randomNumber = random.nextInt(10000);

        // Combine timestamp and random number to form the code
        String code = String.format("%d-%04d", timestamp, randomNumber);

        return code;
    }

}
