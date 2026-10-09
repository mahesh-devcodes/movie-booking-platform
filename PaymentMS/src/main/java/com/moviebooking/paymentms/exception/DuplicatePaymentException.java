package com.moviebooking.paymentms.exception;

public class DuplicatePaymentException extends RuntimeException{

	public DuplicatePaymentException(String message) {
		
		super(message);
	}
}
