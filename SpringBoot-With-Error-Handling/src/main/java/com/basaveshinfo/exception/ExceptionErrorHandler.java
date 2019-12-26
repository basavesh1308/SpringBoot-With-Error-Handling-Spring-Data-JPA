package com.basaveshinfo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.basaveshinfo.ErrorMessage;

@ControllerAdvice
public class ExceptionErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorMessage> NullpointerErrorHandler(Exception ex){
		
		System.out.println("################"+ ex.getMessage());
		
		ErrorMessage er = new ErrorMessage(ex.getMessage(),"Sorry, request employee object is null So can't insert data");
		HttpHeaders headers = new HttpHeaders();
		headers.add("head", "valid and not null input");
		
		return new ResponseEntity<ErrorMessage>(er,headers, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ErrorMessage> errorHandler(Exception ex){
		
		ErrorMessage er = new ErrorMessage(ex.getMessage(),"Due to server shutdown response will be delayed. Thanks!!");
		
		
		return new ResponseEntity<ErrorMessage>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
