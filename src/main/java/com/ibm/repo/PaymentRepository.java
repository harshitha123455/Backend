package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Payment;

/**
 * Repository interface for managing the Payment entity.
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	// No additional methods are defined as JpaRepository provides basic CRUD
	// operations.
}
