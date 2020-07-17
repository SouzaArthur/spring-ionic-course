package br.com.ultracodeultracodejpa.resources.exception;

import java.util.List;

public class FieldsValidation extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldName> nameAndMessageList;
	
	public FieldsValidation(Integer status, String msg, Long timeStamp, List<FieldName> nameAndMessageList) {
		super(status, msg, timeStamp);
		this.nameAndMessageList = nameAndMessageList;
	}
	
	public List<FieldName> getErrors() {
		return nameAndMessageList;
	}
	
}
