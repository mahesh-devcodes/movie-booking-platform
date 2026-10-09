package com.moviebooking.paymentms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull(message = "Booking Id is required")
	private Long bookingId;
	
	@NotNull(message = "Amount is required")
	@DecimalMin(value="0.01",message = "Amount must be greater than zero")
	private BigDecimal amount;
	
	@NotBlank(message = "Payment method is required")
	private String paymentMethod;
	
	public PaymentRequest() {
		
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
