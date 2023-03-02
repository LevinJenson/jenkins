package com.galaxe.AjioClone.exceptions;

/*User Defined Exception class*/

public class NegativePriceException extends RuntimeException{

	public NegativePriceException(String message) {
		super(message);
	}
}
