package com.andregcaires.webstoreapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andregcaires.webstoreapi.services.DatabaseService;
import com.andregcaires.webstoreapi.services.EmailService;
import com.andregcaires.webstoreapi.services.MockEmailService;

/*
 * Configurações específicas para o profile test
 * 
 * */

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DatabaseService _dbService;

	@Bean
	public boolean instanciarDatabase() throws ParseException {
		_dbService.initDatabase();
		return true;
	}
	
	/*
	 * Disponibiliza a classe MockEmailService como instância da interface EmailService a ser injetada
	 * */
	@Bean
	public EmailService email() {
		return new MockEmailService();
	}
}
