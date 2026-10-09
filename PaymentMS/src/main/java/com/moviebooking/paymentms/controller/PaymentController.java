package com.moviebooking.paymentms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.paymentms.dto.PaymentRequest;
import com.moviebooking.paymentms.dto.PaymentResponse;
import com.moviebooking.paymentms.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentResponse createPayment(
			@Valid @RequestBody PaymentRequest request) {
		
		return paymentService.createPayment(request);
	}
	
	@GetMapping
	public List<PaymentResponse> getAllPayments(){
		return paymentService.getAllPayments();
	}
	
	@GetMapping("/{paymentId}")
	public PaymentResponse getPaymentById(
			@PathVariable Long paymentId) {
		
		return paymentService.getPaymentById(paymentId);
	}
	
	@GetMapping("/booking/{bookingId}")
	public PaymentResponse getPaymentByBookingId(@PathVariable Long bookingId){
		
		return paymentService.getPaymentByBookingId(bookingId);
	}
		
	}
