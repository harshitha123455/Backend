package com.ibm.pojo;

/**
 * POJO class representing the login credentials for an admin.
 */
public class AdminLogin {

	private String email;
	private String password;

	/**
	 * Retrieves the email address of the admin.
	 *
	 * @return The email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the admin.
	 *
	 * @param email The email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the admin.
	 *
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the admin.
	 *
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
