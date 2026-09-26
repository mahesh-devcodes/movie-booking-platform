package com.moviebooking.theatrems.service;

import java.util.List;

import com.moviebooking.theatrems.entity.Seat;

public interface SeatService {
	
	Seat createSeat(Seat seat);
	List<Seat> getAllSeats();
	Seat getSeatById(Long seatId);
	Seat updateSeat(Long seatId, Seat seat);
	void deleteSeat(Long seatId);
	
}
