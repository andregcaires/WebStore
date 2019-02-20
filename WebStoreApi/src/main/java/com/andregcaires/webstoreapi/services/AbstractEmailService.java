package com.andregcaires.webstoreapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado. Código: "+ pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPassword);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+ newPassword);
		
		return sm;
	}
}
