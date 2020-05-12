package br.com.ultracodeultracodejpa.domain;

import br.com.ultracodeultracodejpa.domain.enums.PaymentStatusEnum;

public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberInstallments;
	
	public PaymentCard() {}

	public PaymentCard(Integer id, PaymentStatusEnum paymentStatus, Order order, Integer numberInstallments) {
		super(id, paymentStatus, order);
		this.numberInstallments = numberInstallments;
	}

	public Integer getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(Integer numberInstallments) {
		this.numberInstallments = numberInstallments;
	}	
	
}
