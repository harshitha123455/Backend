package com.ibm.service;

import java.util.List;

import com.ibm.entity.Booking;

public interface BookingService {

	int save(Booking b);

	List<Booking> list();

	Booking searchById(int id);

	Boolean remove(int id);
}
