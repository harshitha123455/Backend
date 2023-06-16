package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Booking;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.repo.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository repo;
	private ShowService service;

	@Override
	public int save(Booking b) {
		SeatingArrangement sa = b.getShows().getSeatingArrangement();
		Boolean[] reserved = sa.getReserved();
		for(Integer num: b.getPos()) {
			reserved[num] = true;
			if (num >= normalStart && num<=normalEnd)
				sa.setAvailableNormalSeats(sa.getAvailableNormalSeats()-1);
			else if(num >= executiveStart && num <= executiveEnd)
				sa.setAvailableExecutiveSeats(sa.getAvailableExecutiveSeats()-1);
			else
				sa.setAvailablePremiumSeats(sa.getAvailablePremiumSeats()-1);
			sa.setAvailableSeats(sa.getAvailableSeats()-1);
		}
		repo.save(b);
		return b.getId();
	}

	@Override
	public List<Booking> list() {
		return repo.findAll();
	}

	@Override
	public Booking searchById(int id) throws BookingNotFoundException {
		Booking b;
		try {
			b = repo.findById(id).get();
		} catch (Exception e) {
			throw new BookingNotFoundException(id);
		}
		return b;
	}

	@Override
	public List<Booking> searchByShowId(int id) {
		Shows s = service.searchById(id);
		return repo.findAllByShows(s);
	}

	@Override
	public void remove(int id) throws BookingNotFoundException {
		Booking b;
		try {
			b = repo.findById(id).get();
		} catch (Exception e) {
			throw new BookingNotFoundException(id);
		}
		SeatingArrangement sa = b.getShows().getSeatingArrangement();
		Boolean[] reserved = sa.getReserved();
		for(Integer num: b.getPos()) {
			reserved[num] = true;
			if (num >= normalStart && num<=normalEnd)
				sa.setAvailableNormalSeats(sa.getAvailableNormalSeats()+1);
			else if(num >= executiveStart && num <= executiveEnd)
				sa.setAvailableExecutiveSeats(sa.getAvailableExecutiveSeats()+1);
			else
				sa.setAvailablePremiumSeats(sa.getAvailablePremiumSeats()+1);
			sa.setAvailableSeats(sa.getAvailableSeats()+1);
		}
	}

}
