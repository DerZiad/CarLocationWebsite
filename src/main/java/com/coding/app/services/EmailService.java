package com.coding.app.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailer;
	
	
	private static final String FROM_EMAIL = "sendsmookerzz@gmail.com";
		
	public static String SERVERLINK = "http://localhost";

	
	
	public void sendEmail(HtmlMessage htmlmessage) throws MessagingException {

		MimeMessage message = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		
		helper.setFrom(FROM_EMAIL);
		helper.setTo(htmlmessage.getTo());
		helper.setSubject(htmlmessage.getTopic());
		helper.setText(htmlmessage.generateMessage(), true);
		
		mailer.send(message);

	}
	
	

}