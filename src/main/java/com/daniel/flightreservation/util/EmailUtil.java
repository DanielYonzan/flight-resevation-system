package com.daniel.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.daniel.flightreservation.controllers.UserController;

@Component
public class EmailUtil {
	
	@Value("${com.daniel.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;

	@Value("${com.daniel.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;

	@Autowired
	private JavaMailSender sender;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	public void sentItinerary(String toAddress, String filePath) {
		LOGGER.info("Inside send itinerary");
		MimeMessage message = sender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinearary", new File(filePath));
			
			sender.send(message);
			
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary");
		}
	}
}
