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
	
	public static PaymentStatusEnum toEnum(Integer code) {
		//Verify if it is null
		if(code == null) {
			return null;
		}
		
		//Verify if the passed code is in the Enum list
		for(PaymentStatusEnum x : PaymentStatusEnum.values()) {
			if(x.getCode() == code) {
				return x;
			}
		}
		
		//If it is not, the code returns an error
		throw new IllegalArgumentException("Enum code not found");
	}
	
}
