package br.com.ultracodeultracodejpa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractMailService{
	
	public static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendMail(SimpleMailMessage sm) {
		LOG.info("Simulating send of mail");
		LOG.info(sm.toString());
		LOG.info("Simulation finished");
	}
}
