package com.ibm.service;

import java.util.List;

import com.ibm.entity.Payment;

/**
 * Service interface for managing payments.
 */
public interface PaymentService {

    /**
     * Saves a payment.
     *
     * @param payment the payment to be saved
     * @return the ID of the saved payment
     */
    int save(Payment payment);

    /**
     * Retrieves a list of all payments.
     *
     * @return a list of Payment entities
     */
    List<Payment> list();

    /**
     * Searches for a payment by ID.
     *
     * @param id the ID of the payment to search for
     * @return the found Payment entity, or null if not found
     */
    Payment searchById(int id);

    /**
     * Searches for a payment by transaction ID.
     *
     * @param transactionId the transaction ID of the payment to search for
     * @return the found Payment entity, or null if not found
     */
    Payment searchByTransactionId(Long transactionId);
}
