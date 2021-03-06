package br.com.ultracodeultracodejpa.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.domain.Order;

public interface EmailService {
	
	public void sendOrderConfirmationMail(Order obj);
	
	public void sendMail(SimpleMailMessage sm);
	
	void sendNewPasswordEmail(Client client, String newPass);
}
