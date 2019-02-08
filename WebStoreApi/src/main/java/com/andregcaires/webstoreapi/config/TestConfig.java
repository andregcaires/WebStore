package com.andregcaires.webstoreapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andregcaires.webstoreapi.services.DatabaseService;

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
}
