package com.moviebooking.paymentms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moviebooking.paymentms.config.BookingClient;
import com.moviebooking.paymentms.dto.BookingResponse;
import com.moviebooking.paymentms.dto.PaymentRequest;
import com.moviebooking.paymentms.dto.PaymentResponse;
import com.moviebooking.paymentms.entity.Payment;
import com.moviebooking.paymentms.entity.PaymentStatus;
import com.moviebooking.paymentms.exception.BookingNotFoundException;
import com.moviebooking.paymentms.exception.DuplicatePaymentException;
import com.moviebooking.paymentms.exception.PaymentNotFoundException;
import com.moviebooking.paymentms.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepo;
	private final BookingClient bookingClient;
	
	public PaymentServiceImpl(PaymentRepository paymentRepo,
			BookingClient bookingClient) {
		this.paymentRepo=paymentRepo;
		this.bookingClient=bookingClient;
	}
	
	
	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		
		// Check whether booking exists
		
		BookingResponse booking;
		
		try {
			booking=bookingClient.getBooking(request.getBookingId());
		}catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
			throw new BookingNotFoundException("Booking not found with id: " + request.getBookingId());
			
		}
		
		// check booking status
		if("cancelled".equalsIgnoreCase(booking.getBookingStatus())) {
			throw new IllegalArgumentException("Payment cannot be made for a cancelled booking");
		}
		
		// reject payment for an already confirmed booking
		if("confirmed".equalsIgnoreCase(booking.getBookingStatus())) {
			throw new IllegalArgumentException("Payment cannot be made for an already confirmed booking");
		}
		
		// check duplicate payment
		if(paymentRepo.existsByBookingId(request.getBookingId())) {
			throw new DuplicatePaymentException("Payment already exists for booking id : " + request.getBookingId());
		}
		
		// validate payment amount
		if(request.getAmount().compareTo(booking.getTotalAmount()) !=0) {
			throw new IllegalArgumentException("Payment amount must match booking total amount: " + booking.getTotalAmount());
		}
		
		// create payment
		
		Payment payment=new Payment();
		
		payment.setBookingId(request.getBookingId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setPaymentStatus(PaymentStatus.Success);
		
		payment.setTransactionId("TXN-" + UUID.randomUUID());
		
		payment.setPaymentDate(LocalDateTime.now());
		
		// save payment
		Payment savedPayment=paymentRepo.save(payment);
		
		// confirm booking in BookingMS after successful payment
		if(savedPayment.getPaymentStatus()==PaymentStatus.Success) {
			bookingClient.confirmBooking(request.getBookingId());
		}
		
		// convert to response
		return convertToResponse(savedPayment);
	}

	@Override
	public PaymentResponse getPaymentById(Long paymentId) {
		
		Payment payment=paymentRepo.findById(paymentId)
				.orElseThrow(()-> 
				new PaymentNotFoundException("Payment not found with id: " + paymentId));
		
		return convertToResponse(payment);
	}

	@Override
	public PaymentResponse getPaymentByBookingId(Long bookingId) {
		
		Payment payment=paymentRepo.findByBookingId(bookingId)
				.orElseThrow(()-> 
				new PaymentNotFoundException("Payment not found for booking id: " + bookingId));
				
		return convertToResponse(payment);
	}

	@Override
	public List<PaymentResponse> getAllPayments() {
		
		
		return paymentRepo.findAll()
				.stream()
				.map(this::convertToResponse)
				.collect(Collectors.toList());
	}
	
	private PaymentResponse convertToResponse(Payment payment) {
		
		PaymentResponse response=new PaymentResponse();
		
		response.setPaymentId(payment.getPaymentId());
		response.setBookingId(payment.getBookingId());
		response.setAmount(payment.getAmount());
		response.setPaymentMethod(payment.getPaymentMethod());
		response.setPaymentStatus(payment.getPaymentStatus());
		response.setTransactionId(payment.getTransactionId());
		response.setPaymentDate(payment.getPaymentDate());
		
		return response;
		
	}

}
