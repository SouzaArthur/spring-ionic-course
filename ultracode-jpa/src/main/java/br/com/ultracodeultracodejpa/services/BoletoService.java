package br.com.ultracodeultracodejpa.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.ultracodeultracodejpa.domain.PaymentBoleto;

@Service
public class BoletoService {

	public void fillPaymentWithBoleto(PaymentBoleto payment, Date instOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
	}
}