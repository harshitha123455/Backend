package com.ibm.service;

import java.util.List;

import com.ibm.entity.Booking;
import com.ibm.exception.BookingNotFoundException;

public interface BookingService {
	
	int normalStart = 1;
	int normalEnd = 60;
	int executiveStart = 61;
	int executiveEnd = 80;
	int premiumStart = 81;
	int premiumEnd = 100;

	Booking save(Booking b);

	List<Booking> list();

	Booking searchById(int id) throws BookingNotFoundException;
	
	List<Booking> searchByShowId(int id);

	void remove(int id) throws BookingNotFoundException;
}
