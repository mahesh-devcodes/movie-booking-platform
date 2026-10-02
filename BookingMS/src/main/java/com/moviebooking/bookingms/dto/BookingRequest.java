package com.moviebooking.bookingms.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookingRequest {
	
	@NotNull(message="User Id is required")
	@Positive(message="User Id must be positive")
	private Long userId;
	
	@NotNull(message="Show Id is required")
	@Positive(message="Show Id must be positive")
	private Long showId;
	
	@NotEmpty(message="At least one seat required")
	private List<@NotNull @Positive Long> seatIds;
	
	public BookingRequest() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public List<Long> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(List<Long> seatIds) {
		this.seatIds = seatIds;
	}
	
	
	
}
