package br.com.ultracodeultracodejpa.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpMailService extends AbstractMailService{

	public static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);
	
	@Autowired
	public MailSender mailSender;
	
	@Override
	public void sendMail(SimpleMailMessage sm) {
		LOG.info("Sending e-mail");
		mailSender.send(sm);
		LOG.info("E-mail sended");
		
	}

}
