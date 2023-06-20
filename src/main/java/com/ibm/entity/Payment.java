package com.ibm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Payment class represents an entity that stores payment details.
 */
@Entity
public class Payment {

	@Id
	@GeneratedValue
	private int id;
	private String transactionId;
	private Double amount;

	/**
	 * Retrieves the ID of the payment.
	 *
	 * @return the payment ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the payment.
	 *
	 * @param id the payment ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the transaction ID associated with the payment.
	 *
	 * @return the transaction ID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction ID associated with the payment.
	 *
	 * @param transactionId the transaction ID to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Retrieves the amount of the payment.
	 *
	 * @return the payment amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the payment.
	 *
	 * @param amount the payment amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}