package br.com.ultracodeultracodejpa.domain.enums;

public enum PaymentStatusEnum {

	PENDENTE(1),
	QUITADO(2),
	CANCELADO(3);
	
	private int code;

	private PaymentStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
