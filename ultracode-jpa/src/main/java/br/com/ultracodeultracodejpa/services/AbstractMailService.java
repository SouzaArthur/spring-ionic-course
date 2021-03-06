package br.com.ultracodeultracodejpa.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.ultracodeultracodejpa.domain.Client;
import br.com.ultracodeultracodejpa.domain.Order;

public abstract class AbstractMailService implements EmailService{
	
	@Value("${default.from.email}")
	private String emailFrom;
	
	@Override
	public void sendOrderConfirmationMail(Order obj) {
		SimpleMailMessage sm = prepareMailMessage(obj);
		sendMail(sm);
	}
	
	public SimpleMailMessage prepareMailMessage(Order obj){
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(emailFrom);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setSubject("Order Confirmation");
		sm.setText(obj.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendMail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(emailFrom);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}
