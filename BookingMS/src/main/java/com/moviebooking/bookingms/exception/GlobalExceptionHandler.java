package com.moviebooking.bookingms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 // Booking not found
	@ExceptionHandler(BookingNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleBookingNotFound(
			BookingNotFoundException ex){
		
		Map<String, String> response=new HashMap<>();
		response.put("message", ex.getMessage());
		return response;
	}
	
	// Show not found
	@ExceptionHandler(ShowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleShowNotFound(
            ShowNotFoundException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
	
	// Seat not found
	@ExceptionHandler(SeatNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String>handleSeatNotFound(SeatNotFoundException ex){
		Map<String, String> response=new HashMap<>();
		response.put("message", ex.getMessage());
		return response;
	}
	
	// Validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message",ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .findFirst()
                        .map(error -> error.getDefaultMessage())
                        .orElse("Invalid request"));
        return response;
    }
	
	// Invalid request
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIllegalArgument(IllegalArgumentException ex){
		Map<String, String> response=new HashMap<>();
		response.put("message", ex.getMessage());
		return response;
	}
	
	// Seat already booked / unavailable/booking already cancelled
	@ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleIllegalState(
            IllegalStateException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
	
	// Remote service errors
	@ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String, String> handleRemoteError(
            RestClientResponseException ex) {

        Map<String, String> response = new HashMap<>();
        response.put( "message","ShowMS request failed: " + ex.getStatusCode());
        return response;
    }
	
	// Service unavailable
	@ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Map<String, String> handleServiceUnavailable(
            ResourceAccessException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message", "ShowMS is unavailable");
        return response;
    }
	
	// General exception
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneral(Exception ex) {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Internal server error");
        return response;
    }
}
