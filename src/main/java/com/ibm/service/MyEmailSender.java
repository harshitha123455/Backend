package com.ibm.service;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.entity.Booking;
import com.ibm.entity.User;

@Component
public class MyEmailSender {

    @Autowired
    private EmailService emailService;

    public void sendMyEmail(Booking booking) {
        String recipient = booking.getUser().getEmail();
        String subject = "Booking Confirmation";
        String content = generateEmailContent(booking);

        try {
            emailService.sendEmail(recipient, subject, content);
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }

    private String generateEmailContent(Booking booking) {

        // Load the HTML template file and replace placeholders with actual values
        String emailTemplate = loadEmailTemplate();
        User user = booking.getUser();
        emailTemplate = emailTemplate.replace("[Customer Name]", user.getName());
        emailTemplate = emailTemplate.replace("[Booking ID]", String.valueOf(booking.getId()));
        emailTemplate = emailTemplate.replace("[Amount Paid]", booking.getPayment().getAmount().toString());
        emailTemplate = emailTemplate.replace("[Seats Booked]", booking.getPos().toString());
        emailTemplate = emailTemplate.replace("[Type of Seat]", booking.getType().toString());

        return emailTemplate;
    }

    private String loadEmailTemplate() {
        // Read the email template file (HTML content) and return it as a string
        // You can load the content from a file or define it directly in the code
        // Here's an example of loading it from a file:

        try {
            return Files.readString(Paths.get("emailTemplate/email.html"));
        } catch (Exception e) {
            System.out.println("Failed to load email template. Error: " + e.getMessage());
            return "";
        }
    }
}
