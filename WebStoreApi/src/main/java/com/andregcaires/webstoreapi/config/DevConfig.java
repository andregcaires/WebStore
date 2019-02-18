package com.andregcaires.webstoreapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andregcaires.webstoreapi.services.DatabaseService;
import com.andregcaires.webstoreapi.services.EmailService;
import com.andregcaires.webstoreapi.services.SmtpEmailService;

/*
 * Configurações específicas para o profile dev
 * 
 * */

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DatabaseService _dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instanciarDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		_dbService.initDatabase();
		return true;
	}
	
	/*
	 * Disponibiliza a classe SmtpEmailService como instância da interface EmailService a ser injetada
	 * */
	@Bean
	public EmailService email() {
		return new SmtpEmailService();
	}
}
