package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Payment;
import com.ibm.service.PaymentService;

@CrossOrigin
@RestController
public class PaymentController {

	@Autowired
	PaymentService service;
	
//	http://localhost:8880/admin/payment/all
	@GetMapping(path="/admin/payment/all", produces="application/json")
	public ResponseEntity<List<Payment>> getAllPayment(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).header("Response from", "PaymentController")
				.body(service.list());
	}
}
