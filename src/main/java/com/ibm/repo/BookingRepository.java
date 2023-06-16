package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Booking;
import com.ibm.entity.Shows;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findAllByShows(Shows s);
}
