package br.com.ultracodeultracodejpa.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ultracodeultracodejpa.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbCreateStrategy;
	
	@Bean
	public boolean initiateDataBase() throws ParseException {
		if(dbCreateStrategy.equals("create")) {
			dbService.initiateDataBase();
			return true;
		}
		return false;
	}
}
