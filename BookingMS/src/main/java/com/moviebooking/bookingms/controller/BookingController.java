package com.moviebooking.bookingms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.bookingms.dto.BookingRequest;
import com.moviebooking.bookingms.dto.BookingResponse;
import com.moviebooking.bookingms.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService bookingService;
	
	public BookingController(BookingService bookingService) {
		this.bookingService=bookingService;
	}
	
	@PostMapping
	public BookingResponse createBooking(@Valid @RequestBody BookingRequest request) {
		return bookingService.createBooking(request);
	}
	
	@GetMapping
	public List<BookingResponse> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	@GetMapping("/{bookingId}")
	public BookingResponse getBookingById(@PathVariable Long bookingId) {
		return bookingService.getBookingById(bookingId);
	}
	
	@GetMapping("/user/{userId}")
	public List<BookingResponse> getBookingByUserId(@PathVariable Long userId){
		return bookingService.getBookingsByUserId(userId);
	}
	
	@PutMapping("/{bookingId}/cancel")
	public BookingResponse cancelBooking(@PathVariable Long bookingId) {
		return bookingService.cancelBooking(bookingId);
	}
	
	@PutMapping("/{bookingId}/confirm")
	public BookingResponse confirmBooking(@PathVariable Long bookingId) {
		return bookingService.confirmBooking(bookingId);
	}
	
}

