package com.mindtree.greencard.util;

import java.net.ConnectException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailApi {
	@Autowired
	private JavaMailSender javaMailSender;
	

	public void send(String subject, String body) throws MessagingException, ConnectException {
MimeMessage message = javaMailSender.createMimeMessage();
MimeMessageHelper helper;

helper = new MimeMessageHelper(message, true);
helper.setSubject(subject);
helper.setTo("sayeed150696@gmail.com");
helper.setText(body, true); 

javaMailSender.send(message);
	}
}
