package com.ibm.service;

import java.util.List;

import com.ibm.entity.Payment;

public interface PaymentService {

	int save(Payment p);

	List<Payment> list();

	Payment searchById(int id);

	Payment searchByTransactionId(Long id);
}
