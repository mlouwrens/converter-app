package com.local.app.ws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.local.app.ws.ui.models.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {InvalidOptionException.class})
	public ResponseEntity<Object> handleInvalidOptionException(Exception ex, WebRequest request) {

		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
