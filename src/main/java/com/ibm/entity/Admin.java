package com.ibm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * The Admin class represents an entity that stores admin details.
 */
@Entity
public class Admin {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(length = 10)
	private String contactNumber;
	private String email;
	private String passwordHash;
	@Transient
	private String password;

	/**
	 * Retrieves the ID of the admin.
	 *
	 * @return the admin ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the admin.
	 *
	 * @param id the admin ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the admin.
	 *
	 * @return the admin name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the admin.
	 *
	 * @param name the admin name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the contact number of the admin.
	 *
	 * @return the admin contact number
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number of the admin.
	 *
	 * @param contactNumber the admin contact number to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Retrieves the email of the admin.
	 *
	 * @return the admin email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the admin.
	 *
	 * @param email the admin email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password hash of the admin.
	 *
	 * @return the admin password hash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Sets the password hash of the admin.
	 *
	 * @param passwordHash the admin password hash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Retrieves the password of the admin.
	 *
	 * @return the admin password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the admin.
	 *
	 * @param password the admin password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
