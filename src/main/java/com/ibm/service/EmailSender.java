package com.ibm.service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ibm.entity.Booking;
import com.ibm.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Component class for sending email notifications.
 */
@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TimeTableService tService;

	/**
	 * Sends an email confirmation for the given booking.
	 *
	 * @param booking the booking for which to send the email
	 */
	public void sendMyEmail(Booking booking) {
		String recipient = booking.getUser().getEmail();
		String subject = "Booking Confirmation";
		String content = generateEmailContent(booking);

		try {
			URI uri = new URI(booking.getShows().getMovie().getImageUrl());
			sendEmailWithAttachment(recipient, subject, content, uri.getPath().substring(1));
			System.out.println("Email sent successfully.");
		} catch (Exception e) {
			System.out.println("Failed to send email. Error: " + e.getMessage());
		}
	}

	/**
	 * Sends an email with an attachment.
	 *
	 * @param recipient          the email recipient
	 * @param subject            the email subject
	 * @param content            the email content
	 * @param attachmentFilePath the file path of the attachment
	 * @throws MessagingException if an error occurs while sending the email
	 */
	private void sendEmailWithAttachment(String recipient, String subject, String content, String attachmentFilePath)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(content, true);

		FileSystemResource file = new FileSystemResource(attachmentFilePath);
		helper.addAttachment(file.getFilename(), file);

		mailSender.send(message);
	}

	/**
	 * Generates the email content for the booking confirmation.
	 *
	 * @param booking the booking for which to generate the email content
	 * @return the generated email content as a string
	 */
	private String generateEmailContent(Booking booking) {
		// Load the HTML template file and replace placeholders with actual values
		String emailTemplate = loadEmailTemplate();
		User user = booking.getUser();
		emailTemplate = emailTemplate.replace("[Customer Name]", user.getName());
		emailTemplate = emailTemplate.replace("[Movie Name]", booking.getShows().getMovie().getName());
		emailTemplate = emailTemplate.replace("[Screen Name]",
				tService.searchByShow(booking.getShows().getId()).getScreen().getName());
		emailTemplate = emailTemplate.replace("[Screen Date]",
				tService.searchByShow(booking.getShows().getId()).getDate().toString());
		emailTemplate = emailTemplate.replace("[Screen Time]", booking.getShows().getTime());
		emailTemplate = emailTemplate.replace("[Booking ID]", String.valueOf(booking.getId()));
		emailTemplate = emailTemplate.replace("[Amount Paid]", booking.getPayment().getAmount().toString());
		emailTemplate = emailTemplate.replace("[Seats Booked]", booking.getPos().toString());
		emailTemplate = emailTemplate.replace("[Type of Seat]", booking.getTypes().toString());

		return emailTemplate;
	}

	/**
	 * Loads the email template from a file.
	 *
	 * @return the loaded email template as a string
	 */
	private String loadEmailTemplate() {
		// Read the email template file (HTML content) and return it as a string
		try {
			return Files.readString(Paths.get("emailTemplate/email.html"));
		} catch (Exception e) {
			System.out.println("Failed to load email template. Error: " + e.getMessage());
			return "";
		}
	}
}
