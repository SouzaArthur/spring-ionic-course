package br.com.ultracodeultracodejpa.services.exception;

public class EmailAlreadyExists extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExists(String msg) {
		super(msg);
	}
	
	public EmailAlreadyExists(String msg, Throwable cause) {
		super(msg, cause);
	}
}
