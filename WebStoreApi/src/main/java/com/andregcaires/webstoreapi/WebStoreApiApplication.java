package com.andregcaires.webstoreapi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebStoreApiApplication implements CommandLineRunner {
	

	
	public static void main(String[] args) {
		SpringApplication.run(WebStoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Aplicação iniciada: "
							+ new SimpleDateFormat("dd/MM/yyy HH:mm")
							.parse(new Date(System.currentTimeMillis()).toString()));
		
	}
	
}
