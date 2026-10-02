package com.moviebooking.bookingms.service;

import java.util.List;

import com.moviebooking.bookingms.dto.BookingRequest;
import com.moviebooking.bookingms.dto.BookingResponse;

public interface BookingService {
	
	BookingResponse createBooking(BookingRequest request);
	List<BookingResponse> getAllBookings();
	BookingResponse getBookingById(Long bookingId);
	List<BookingResponse> getBookingsByUserId(Long userId);
	BookingResponse cancelBooking(Long bookingId);
	
}
