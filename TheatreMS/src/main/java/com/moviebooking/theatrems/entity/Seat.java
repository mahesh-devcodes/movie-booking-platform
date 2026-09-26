package com.moviebooking.theatrems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="seat")
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seat_id")
	private Long seatId;
	
	@Column(name="seat_number",nullable = false)
	private String seatNumber;
	
	@Column(name="seat_type",nullable = false)
	private String seatType;
	
	@Column(name="seat_status",nullable = false)
	private String seatStatus;
	
	@Column(name="screen_id",nullable = false)
	private Long screenId;
	
	public Seat() {
		
	}

	public Seat(Long seatId, String seatNumber, String seatType, String seatStatus, Long screenId) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.seatStatus = seatStatus;
		this.screenId = screenId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}
	
	
}
