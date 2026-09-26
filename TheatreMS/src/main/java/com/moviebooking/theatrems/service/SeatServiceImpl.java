package com.moviebooking.theatrems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.theatrems.entity.Seat;
import com.moviebooking.theatrems.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {
	@Autowired
	private SeatRepository seatRepo;
	
	@Override
	public Seat createSeat(Seat seat) {
		
		return seatRepo.save(seat);
	}

	@Override
	public List<Seat> getAllSeats() {
		
		return seatRepo.findAll();
	}

	@Override
	public Seat getSeatById(Long seatId) {
		
		return seatRepo.findById(seatId).
				orElseThrow(()->new RuntimeException("Seat not found with Id"+seatId));
	}

	@Override
	public Seat updateSeat(Long seatId, Seat seat) {
		Seat existingSeat=seatRepo.findById(seatId)
				.orElseThrow(()->new RuntimeException("Seat not found with ID"+seatId));
		
		existingSeat.setSeatNumber(seat.getSeatNumber());
		existingSeat.setSeatType(seat.getSeatType());
		existingSeat.setSeatStatus(seat.getSeatStatus());
		existingSeat.setScreenId(seat.getScreenId());
		return seatRepo.save(existingSeat);
	}

	@Override
	public void deleteSeat(Long seatId) {
		Seat existingSeat=seatRepo.findById(seatId)
				.orElseThrow(()->new RuntimeException("Seat not found with ID"+seatId));
		seatRepo.delete(existingSeat);
	}

}
