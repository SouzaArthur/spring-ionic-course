package br.com.ultracodeultracodejpa.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ultracodeultracodejpa.services.DBService;
import br.com.ultracodeultracodejpa.services.EmailService;
import br.com.ultracodeultracodejpa.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;
	
	@Bean
	public boolean initiateDataBase() throws ParseException {
		dbService.initiateDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService(){
		return new MockMailService();
	}
}
