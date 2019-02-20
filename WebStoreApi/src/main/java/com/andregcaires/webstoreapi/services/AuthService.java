package com.andregcaires.webstoreapi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository _clienteRepo;
	
	@Autowired
	private BCryptPasswordEncoder _bCryptEncoder;
	
	@Autowired
	private EmailService _emailService;
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = _clienteRepo.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPassword = newPassword();
		
		cliente.setSenha(_bCryptEncoder.encode(newPassword));

		_clienteRepo.save(cliente);
		
		_emailService.sendNewPasswordEmail(cliente, newPassword);
	}

	/*
	 * Gera senha aleatória
	 * */
	private String newPassword() {
		char[] vet = new char[10];
		
		for(int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		Random rand = new Random();
		int opt = rand.nextInt(3);
		if(opt == 0) { // gera digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
}
