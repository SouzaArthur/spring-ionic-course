package br.com.ultracodeultracodejpa.resources.exception;

import java.util.List;

public class FieldsValidation extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldName> nameAndMessage;
	
	public FieldsValidation(Integer status, String msg, Long timeStamp, List<FieldName> nameAndMessage) {
		super(status, msg, timeStamp);
		this.nameAndMessage = nameAndMessage;
	}
	
	public List<FieldName> getErrors() {
		return nameAndMessage;
	}
	
}
