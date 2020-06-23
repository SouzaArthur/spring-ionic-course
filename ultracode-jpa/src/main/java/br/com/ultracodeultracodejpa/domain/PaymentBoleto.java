package br.com.ultracodeultracodejpa.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ultracodeultracodejpa.domain.enums.PaymentStatusEnum;

@Entity
public class PaymentBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	
	public PaymentBoleto() {}

	public PaymentBoleto(Integer id, PaymentStatusEnum paymentStatus, Order order, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
