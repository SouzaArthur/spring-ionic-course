package br.com.ultracodeultracodejpa.services.exception;

public class DataIntegrityViolation extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityViolation(String msg) {
		super(msg);
	}
	
	public DataIntegrityViolation(String msg, Throwable cause) {
		super(msg, cause);
	}
}
