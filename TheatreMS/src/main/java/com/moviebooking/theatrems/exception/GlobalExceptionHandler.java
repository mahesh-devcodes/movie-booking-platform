package com.moviebooking.theatrems.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ScreenNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handlerScreenNotFound(
			ScreenNotFoundException ex){
		
		Map<String, String> response=new HashMap();
		response.put("message", ex.getMessage());
		
		return response;
	}
	
	@ExceptionHandler(TheatreNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleTheatreNotFound(
	        TheatreNotFoundException ex) {

	    Map<String, String> response = new HashMap<>();

	    response.put("message", ex.getMessage());

	    return response;
	}
	
	@ExceptionHandler(SeatNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleSeatNotFound(
	        SeatNotFoundException ex) {

	    Map<String, String> response = new HashMap<>();

	    response.put("message", ex.getMessage());

	    return response;
	}
}
