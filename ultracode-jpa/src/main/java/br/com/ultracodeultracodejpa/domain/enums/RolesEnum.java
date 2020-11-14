package br.com.ultracodeultracodejpa.domain.enums;

public enum RolesEnum {

	CLIENT(1, "ROLE_CLIENT"),
	ADMIN(2, "ROLE_ADMIN");
	
	private int code;
	private String description;

	private RolesEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
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
