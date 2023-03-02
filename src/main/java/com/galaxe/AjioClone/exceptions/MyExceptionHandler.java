package com.galaxe.AjioClone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*Exception handler class*/

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ErrorMessage> getExceptionData(ItemNotFoundException exception){
		ErrorMessage errorMessage = new ErrorMessage(404,exception.getMessage());
		return new ResponseEntity<>(errorMessage ,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NegativePriceException.class)
	public ResponseEntity<ErrorMessage> getExceptionData(NegativePriceException exception){
		ErrorMessage errorMessage = new ErrorMessage(406,exception.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler(OfferPriceException.class)
	public ResponseEntity<ErrorMessage> getExceptionData(OfferPriceException exception){
		ErrorMessage errorMessage = new ErrorMessage(406,exception.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_ACCEPTABLE);
	}
	
	
}
