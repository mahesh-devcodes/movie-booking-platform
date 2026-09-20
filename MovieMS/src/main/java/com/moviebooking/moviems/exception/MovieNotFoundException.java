package com.moviebooking.moviems.exception;

public class MovieNotFoundException extends RuntimeException {
	
	public MovieNotFoundException(String message) {
		super(message);
	}

}
