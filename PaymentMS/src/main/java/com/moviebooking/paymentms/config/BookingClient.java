package com.moviebooking.paymentms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.moviebooking.paymentms.dto.BookingResponse;

@Component
public class BookingClient {
	
	private final RestClient bookingRestClient;
	
	public BookingClient(RestClient bookingRestClient) {
		this.bookingRestClient=bookingRestClient;
	}
	
	public BookingResponse getBooking(Long bookingId) {
		
		return bookingRestClient
				.get()
				.uri("/{id}",bookingId)
				.retrieve()
				.body(BookingResponse.class);
	}
	
	public void confirmBooking(Long bookingId) {
		bookingRestClient.put()
					.uri("/{bookingId}/confirm",bookingId)
					.retrieve()
					.toBodilessEntity();
	}
}
