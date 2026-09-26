package com.moviebooking.theatrems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.theatrems.entity.Seat;
import com.moviebooking.theatrems.service.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Seat createSeat(@RequestBody Seat seat) {
		return seatService.createSeat(seat);
	}
	
	@GetMapping
	public List<Seat> getAllSeats(){
		return seatService.getAllSeats();
	}
	
	@GetMapping("/{seatId}")
	public Seat getSeatById(@PathVariable Long seatId) {
		return seatService.getSeatById(seatId);
	}
	
	@PutMapping("/{seatId}")
	public Seat updateSeat(@PathVariable Long seatId,
			@RequestBody Seat seat) {
		return seatService.updateSeat(seatId, seat);
	}
	
	@DeleteMapping("/{seatId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSeat(@PathVariable Long seatId) {
		seatService.deleteSeat(seatId);
	}
}
