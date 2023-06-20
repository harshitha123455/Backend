package com.ibm.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ibm.service.EmailService;

/**
 * The EmailConfig class is a configuration class that sets up the email
 * configuration based on the host, port, username, and password. It provides
 * the necessary beans for sending emails using JavaMailSender.
 */
@Configuration
public class EmailConfig {

	private Environment environment;

	/**
	 * Constructs an EmailConfig object with the specified environment.
	 *
	 * @param environment the environment containing the email configuration
	 *                    properties
	 */
	public EmailConfig(Environment environment) {
		this.environment = environment;
	}

	/**
	 * Configures and creates a JavaMailSender bean.
	 *
	 * @return the configured JavaMailSender instance
	 */
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
		mailSender.setUsername(environment.getProperty("spring.mail.username"));
		mailSender.setPassword(environment.getProperty("spring.mail.password"));

		// Set additional properties
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
		javaMailProperties.put("mail.smtp.starttls.enable",
				environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}

	/**
	 * Creates an EmailService bean with the provided JavaMailSender.
	 *
	 * @param mailSender the JavaMailSender instance
	 * @return the created EmailService instance
	 */
	@Bean
	public EmailService emailService(JavaMailSender mailSender) {
		return new EmailService(mailSender, environment);
	}
}
