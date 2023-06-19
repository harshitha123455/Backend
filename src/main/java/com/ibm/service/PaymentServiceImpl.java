package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Payment;
import com.ibm.repo.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repo;
	
	@Override
	public int save(Payment p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> list() {
		return repo.findAll();
	}

	@Override
	public Payment searchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment searchByTransactionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
