package com.coding.app.services;

import com.coding.app.models.enums.EmailType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class EmailService {

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Value("${server.dns}")
	public static String serverLink;

	private final JavaMailSender mailer;

	public void sendEmail(Email email) throws MessagingException {
		MimeMessage message = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		
		helper.setFrom(fromEmail);
		helper.setTo(email.to);
		helper.setSubject(email.subject);
		helper.setText(email.generateMessage(), true);
		
		mailer.send(message);
	}

	public record Email(String to, String subject, EmailType type, HashMap<String, String> bodyFields) {

		private static final String HTML_TEMPLATE_CONFIRMATION = "classpath:templates/confirmation_letter.html";
		private static final String HTML_TEMPLATE_RESET = "classpath:templates/recover_letter.html";

		public String generateMessage() {
			final ResourceLoader resourceLoader = new DefaultResourceLoader();
			final String templatePath = type == EmailType.CONFIRMATION ? HTML_TEMPLATE_CONFIRMATION : HTML_TEMPLATE_RESET;
			try {
				var resource = resourceLoader.getResource(templatePath);
				try (var in = resource.getInputStream()) {
					String html = new String(in.readAllBytes());
					for(var entry : bodyFields.entrySet()) {
						html = html.replace("${" + entry.getKey() + "}", entry.getValue());
					}
					return html;
				}
			} catch (Exception e) {
					return "<p>Unable to load email template.</p>";
			}
		}
	}

}