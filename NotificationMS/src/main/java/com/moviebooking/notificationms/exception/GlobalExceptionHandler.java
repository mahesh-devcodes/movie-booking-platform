package com.moviebooking.notificationms.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle notification not found
	@ExceptionHandler(NotificationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleNotFound(NotificationNotFoundException ex){
		
		return buildError(
		HttpStatus.NOT_FOUND, ex.getMessage(),new HashMap<>()
		);
	}
	
	// Handle validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleValidation(MethodArgumentNotValidException ex){
		
		Map<String, String> errors=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> 	
			errors.put(fieldError.getField(), fieldError.getDefaultMessage()
			));
	
	return buildError(
			HttpStatus.BAD_REQUEST,"Validation failed", errors);
	}
	
	// Handle path variable validation errors
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleConstraintViolation(
			ConstraintViolationException ex){
		Map<String, String> errors=new HashMap<>();
		ex.getConstraintViolations().forEach(violation ->
		errors.put(violation.getPropertyPath().toString(),
				violation.getMessage()));
		
		return buildError(
				HttpStatus.BAD_REQUEST,
				"Path variable validation failed", errors);
	}
	
	// Handle spring MVC method validation errors
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleMethodValidation(HandlerMethodValidationException ex){
		
		return buildError(
				HttpStatus.BAD_REQUEST, "Path variable validation failed", new HashMap<>());
	}
	
	// Handle invalid JSON and enum values
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleInvalidRequest(HttpMessageNotReadableException ex){
		
		
		return buildError(
				HttpStatus.BAD_REQUEST,"Invalid request body or notification type", new HashMap<>());
	}
	
	// Handle unexpected exceptions
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleGlobalException(Exception ex){
		
		return buildError(
				HttpStatus.INTERNAL_SERVER_ERROR,
				"An unexpected error occurred", new HashMap<>());
				
	}
	
	// common error response structure
	private Map<String, Object> buildError(
			HttpStatus status, String message, Map<String, String> details){
		
		Map<String, Object> error =new LinkedHashMap<>();
		
		error.put("timestamp", LocalDateTime.now());
		error.put("status", status.value());
		error.put("error", status.getReasonPhrase());
		error.put("message", message);
		error.put("details", details);
		
		return error;
	}
}



