package br.com.ultracodeultracodejpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ultracodeultracodejpa.domain.PaymentBoleto;
import br.com.ultracodeultracodejpa.domain.PaymentCard;

@Configuration
public class JacksonConfig {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentBoleto.class);
				objectMapper.registerSubtypes(PaymentCard.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}