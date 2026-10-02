package com.moviebooking.bookingms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviebooking.bookingms.dto.BookingRequest;
import com.moviebooking.bookingms.dto.BookingResponse;
import com.moviebooking.bookingms.entity.Booking;
import com.moviebooking.bookingms.entity.BookingSeat;
import com.moviebooking.bookingms.entity.BookingStatus;
import com.moviebooking.bookingms.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepo;
	
	public BookingServiceImpl(BookingRepository bookingRepo) {
		this.bookingRepo=bookingRepo;
	}
	
	@Override
	@Transactional
	public BookingResponse createBooking(BookingRequest request) {
		
		//create booking object
		Booking booking=new Booking();
		
		booking.setUserId(request.getUserId());
		booking.setShowId(request.getShowId());
		booking.setBookingDate(LocalDateTime.now());
		
		//Temporary ticket price : 200 per seat
		BigDecimal ticketPrice=new BigDecimal("250.00");
		
		BigDecimal totalAmount=ticketPrice.multiply(
				BigDecimal.valueOf(request.getSeatIds().size()));
		
		booking.setTotalAmount(totalAmount);
		
		//set initial booking status
		booking.setBookingStatus(BookingStatus.Pending);
		
		// add selected seats to booking
		for(Long seatId : request.getSeatIds()) {
			
			BookingSeat bookingSeat=new BookingSeat();
		
			bookingSeat.setSeatId(seatId);
			bookingSeat.setBooking(booking);
		
			booking.getBookingSeats().add(bookingSeat);
		}
		
		//save booking and associated seats
		Booking savedBooking=bookingRepo.save(booking);
		// Convert entity into response DTO
		return mapToResponse(savedBooking);
	}

	@Override
	public List<BookingResponse> getAllBookings() {
		
		return bookingRepo.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	@Override
	public BookingResponse getBookingById(Long bookingId) {
		
		Booking booking =bookingRepo.findById(bookingId)
				.orElseThrow(()->new RuntimeException("Booking Id not found with ID: "+bookingId));
						
		return mapToResponse(booking);
	}

	@Override
	public List<BookingResponse> getBookingsByUserId(Long userId) {
		
		return bookingRepo.findByUserId(userId)
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	@Override
	@Transactional
	public BookingResponse cancelBooking(Long bookingId) {
		Booking booking=bookingRepo.findById(bookingId)
				.orElseThrow(()->new RuntimeException("Booking not found with Id:"+bookingId));
		if(booking.getBookingStatus() == BookingStatus.Cancelled) {
			throw new IllegalStateException("Boooking is already cancelled");
		}
		
		booking.setBookingStatus(BookingStatus.Cancelled);
		Booking updatedBooking=bookingRepo.save(booking);
		
		return mapToResponse(updatedBooking);
	}
	
	//Convert Booking Entity into BooingResponse
	
	private BookingResponse mapToResponse(Booking booking) {
		BookingResponse response=new BookingResponse();
		
		response.setBookingId(booking.getBookingId());
		response.setUserId(booking.getUserId());
		response.setShowId(booking.getShowId());
		
		response.setSeatIds(booking.getBookingSeats()
				.stream()
				.map(BookingSeat::getSeatId)
				.toList()
				);
		
		response.setBookingDate(booking.getBookingDate());
		response.setTotalAmount(booking.getTotalAmount());
		response.setBookingStatus(booking.getBookingStatus());
		
		return response;
	}

}
