package br.com.ultracodeultracodejpa.resources.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ultracodeultracodejpa.services.exception.AuthorizationException;
import br.com.ultracodeultracodejpa.services.exception.DataIntegrityViolation;
import br.com.ultracodeultracodejpa.services.exception.EmailAlreadyExists;
import br.com.ultracodeultracodejpa.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolation.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolation e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<FieldsValidation> fieldValidation(MethodArgumentNotValidException e, HttpServletRequest request){
		List<FieldName> fieldNameList = new ArrayList<>();
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			fieldNameList.add(new FieldName(x.getField(),x.getDefaultMessage()));
		}
		
		FieldsValidation err = new FieldsValidation(HttpStatus.BAD_REQUEST.value(), "Fields with error or missing", System.currentTimeMillis(), fieldNameList);		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(EmailAlreadyExists.class)
	public ResponseEntity<FieldsValidation> emailAlreadyExists(EmailAlreadyExists e, HttpServletRequest request){
		List<FieldName> fieldName = new ArrayList<>();
		fieldName.add(0, new FieldName("email", "The given email already exists"));
		FieldsValidation err = new FieldsValidation(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis(), fieldName);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
		
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
