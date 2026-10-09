package com.moviebooking.paymentms.service;

import java.util.List;

import com.moviebooking.paymentms.dto.PaymentRequest;
import com.moviebooking.paymentms.dto.PaymentResponse;

public interface PaymentService {
	
	PaymentResponse createPayment(PaymentRequest request);
	PaymentResponse getPaymentById(Long paymentId);
	PaymentResponse getPaymentByBookingId(Long bookingId);
	List<PaymentResponse> getAllPayments();
	
	
}
