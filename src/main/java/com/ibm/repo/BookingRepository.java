package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
