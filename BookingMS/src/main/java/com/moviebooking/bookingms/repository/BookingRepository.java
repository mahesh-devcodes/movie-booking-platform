package com.moviebooking.bookingms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.bookingms.entity.Booking;
import com.moviebooking.bookingms.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long userId);
	
	boolean existsByShowIdAndBookingSeats_SeatIdAndBookingStatusIn(Long showId,
			Long seatId,
			List<BookingStatus> statuses);
}
