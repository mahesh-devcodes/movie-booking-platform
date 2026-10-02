package com.moviebooking.bookingms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking_seat")
public class BookingSeat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_seat_id")
	private Long bookingSeatId;
	
	@Column(name="seat_id", nullable = false)
	private Long seatId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="booking_id", nullable = false)
	private Booking booking;
	
	public BookingSeat() {	
	
	}

	public Long getBookingSeatId() {
		return bookingSeatId;
	}

	public void setBookingSeatId(Long bookingSeatId) {
		this.bookingSeatId = bookingSeatId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
}
