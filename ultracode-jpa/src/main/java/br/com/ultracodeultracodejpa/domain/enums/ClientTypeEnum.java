package br.com.ultracodeultracodejpa.domain.enums;

public enum ClientTypeEnum {

	PESSOAJURIDICA(1),
	PESSOAFISICA(2);
	
	private int code;

	private ClientTypeEnum(Integer code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ClientTypeEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(ClientTypeEnum x: ClientTypeEnum.values()) {
			if(code == x.getCode()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código não encontrado.");
		
		
	}
	
	
}
