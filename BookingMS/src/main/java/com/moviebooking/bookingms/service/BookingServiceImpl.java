package com.moviebooking.bookingms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviebooking.bookingms.config.ShowClient;
import com.moviebooking.bookingms.config.TheatreClient;
import com.moviebooking.bookingms.dto.BookingRequest;
import com.moviebooking.bookingms.dto.BookingResponse;
import com.moviebooking.bookingms.dto.SeatResponse;
import com.moviebooking.bookingms.dto.ShowResponse;
import com.moviebooking.bookingms.entity.Booking;
import com.moviebooking.bookingms.entity.BookingSeat;
import com.moviebooking.bookingms.entity.BookingStatus;
import com.moviebooking.bookingms.exception.BookingNotFoundException;
import com.moviebooking.bookingms.exception.SeatNotFoundException;
import com.moviebooking.bookingms.exception.ShowNotFoundException;
import com.moviebooking.bookingms.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepo;
	private final ShowClient showClient;
	private final TheatreClient theatreClient;
	
	public BookingServiceImpl(BookingRepository bookingRepo,
			ShowClient showClient,
			TheatreClient theatreClient) {
		this.bookingRepo=bookingRepo;
		this.showClient=showClient;
		this.theatreClient=theatreClient;
	}
	
	// create booking
	
	@Override
	@Transactional
	public BookingResponse createBooking(BookingRequest request) {
		
		// Validate the show
		
		ShowResponse show=showClient.getShowById(request.getShowId());
		
		if(show==null || show.getShowId()==null) {
			throw new ShowNotFoundException(
					"Show not found with Id: " + request.getShowId());
		}
		
		// validate Seat Ids are provided
		
		if(request.getSeatIds()==null 
				|| request.getSeatIds().isEmpty()) {
			throw new SeatNotFoundException("At least one seat must be selected");
		}
		
		// duplicate seat validation
		if(request.getSeatIds().stream().distinct().count()
				!=request.getSeatIds().size()) {
			throw new IllegalArgumentException("Duplicate seat IDs are not allowed");
		}
		
		// validate every seat
		for(Long seatId : request.getSeatIds()) {
			validateSeat(seatId, show.getScreenId());
			validateSeatNotBooked(request.getShowId(),
					seatId);
		}
		
		// calculate total amount
		BigDecimal totalAmount=calculateAmount(request.getSeatIds());
		
		//create booking object
		Booking booking=new Booking();
		
		booking.setUserId(request.getUserId());
		booking.setShowId(request.getShowId());
		booking.setBookingDate(LocalDateTime.now());
		
		booking.setTotalAmount(totalAmount);
		
		// initial status
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
		
		// update seat status in theatreMS
		for(Long seatId : request.getSeatIds()) {
			
			theatreClient.updateSeatStatus(seatId, "Booked");
		}
		
		// Convert entity into response DTO
		return mapToResponse(savedBooking);
	}
	
	//new validation
	private void validateSeat( Long seatId,Long screenId) {
	
	SeatResponse seat=theatreClient.getSeatById(seatId);
	
	// seat doesn't exist
	if(seat==null || seat.getSeatId()==null) {
		throw new SeatNotFoundException("Seat not found with Id: " +seatId);
	}
	
	// Seat belongs to another screen
	if(!screenId.equals(seat.getScreenId())) {
		throw new SeatNotFoundException("Seat " + seatId 
				+ "does not belong to screen" 
				+ screenId);
	}
	
	// seat is not available
	if(seat.getSeatStatus() == null
	        || !seat.getSeatStatus().equalsIgnoreCase("Available")) {

	    throw new IllegalStateException(
	            "Seat " + seatId + " is not available");
	}
	}
	
	private void validateSeatNotBooked(Long showId, Long seatId) {
		
		List<BookingStatus> blockingStatus=List.of(
				BookingStatus.Pending,
				BookingStatus.Confirmed);
		
		boolean alreadyBooked=bookingRepo.existsByShowIdAndBookingSeats_SeatIdAndBookingStatusIn(showId, seatId, blockingStatus);
		
		if(alreadyBooked) {
			throw new IllegalStateException(
					"Seat " + seatId + "is already booked for show"
					+ showId);
		}
	}
	
	//calculate Total amount
	private BigDecimal calculateAmount(List<Long> seatIds) {
		BigDecimal ticketPrice=new BigDecimal("250.00");
		return ticketPrice.multiply(BigDecimal.valueOf(seatIds.size()));
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
				.orElseThrow(()->new BookingNotFoundException("Booking Id not found with Id: " +bookingId));
						
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
				.orElseThrow(()->new BookingNotFoundException("Booking not found with Id:"+bookingId));
		
		if(booking.getBookingStatus() == BookingStatus.Cancelled) {
			throw new IllegalStateException("Booking is already cancelled");
		}
		
		booking.setBookingStatus(BookingStatus.Cancelled);
		
		// release seats in theatreMS
		for(BookingSeat bookingSeat:booking.getBookingSeats()) {
			
			theatreClient.updateSeatStatus(bookingSeat.getSeatId(), "Available");
		}
		
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

	@Override
	@Transactional
	public BookingResponse confirmBooking(Long bookingId) {
		
		Booking booking=bookingRepo.findById(bookingId).orElseThrow(()->
		new BookingNotFoundException("Booking not found with Id : " +bookingId));
		
		// Cannot confirm an already cancelled booking
		if(booking.getBookingStatus()==BookingStatus.Cancelled) {
			
			throw new IllegalStateException("Cancelled booking cannot be confirmed");
	
		}
		
		// Cannot confirm an already confirmed booking
		if(booking.getBookingStatus()==BookingStatus.Confirmed) {
			
			throw new IllegalStateException("Booking already confirmed");
		}
		
		// Pending -> confirmed
		
		booking.setBookingStatus(BookingStatus.Confirmed);
		Booking updatedBooking=bookingRepo.save(booking);
		
		return mapToResponse(updatedBooking);
	}

}
