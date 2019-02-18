package com.andregcaires.webstoreapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	/*
	 * Injeta objeto que instancia todos os dados de email no arquivo .properties
	 * */	
	@Autowired
	private MailSender _mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email...");
		_mailSender.send(msg);
		LOG.info("Email enviado");
	}

}
