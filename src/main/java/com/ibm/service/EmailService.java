package com.ibm.service;

import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Service class for sending emails.
 */
public class EmailService {

	private JavaMailSender mailSender;
	private Environment environment;

	/**
	 * Constructs an EmailService with the specified JavaMailSender and Environment.
	 *
	 * @param mailSender  the JavaMailSender instance to use for sending emails
	 * @param environment the Environment instance for retrieving email
	 *                    configuration properties
	 */
	public EmailService(JavaMailSender mailSender, Environment environment) {
		this.mailSender = mailSender;
		this.environment = environment;
	}

	/**
	 * Sends an email with the given recipient, subject, and content.
	 *
	 * @param recipient the recipient email address
	 * @param subject   the email subject
	 * @param content   the email content
	 */
	public void sendEmail(String recipient, String subject, String content) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(recipient);
			helper.setSubject(subject);
			helper.setText(content, true); // true indicates HTML content

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the property value for the given key from the Environment.
	 *
	 * @param key the key of the property to retrieve
	 * @return the value of the property, or null if not found
	 */
	public String getProperty(String key) {
		return environment.getProperty(key);
	}
}
