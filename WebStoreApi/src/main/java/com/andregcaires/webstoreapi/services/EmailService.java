package com.andregcaires.webstoreapi.services;

import org.springframework.mail.SimpleMailMessage;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPassword);
	
	
}
