package com.bridgelabz.fundoonotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.MailTemplate;
@Service
public class MailService {
	@Autowired
	JavaMailSender javaMailSender;
     String url="http://localhost:8080/verify/";
	public void sendMail(String toMail, String response) {
		try {
			SimpleMailMessage simpleMsg = new SimpleMailMessage();
			simpleMsg.setTo(toMail);
			simpleMsg.setSubject("Verify mail");
			System.out.println(toMail);
			simpleMsg.setText(url+response);
			javaMailSender.send(simpleMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
