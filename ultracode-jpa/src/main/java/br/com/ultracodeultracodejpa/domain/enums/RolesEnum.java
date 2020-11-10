package br.com.ultracodeultracodejpa.domain.enums;

public enum RolesEnum {

	CLIENT(1, "ROLE_CLIENT"),
	ADMIN(2, "ROLE_ADMIN");
	
	private int code;

	private RolesEnum(Integer code, String role) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static RolesEnum toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(RolesEnum x: RolesEnum.values()) {
			if(code == x.getCode()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código não encontrado.");
		
		
	}
	
	
}
