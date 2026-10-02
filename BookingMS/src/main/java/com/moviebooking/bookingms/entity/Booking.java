package com.moviebooking.bookingms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Long bookingId;
	
	@Column(name="user_id", nullable = false)
	private Long userId;
	
	@Column(name="show_id", nullable = false)
	private Long showId;
	
	@Column(name="booking_date", nullable = false)
	private LocalDateTime bookingDate;
	
	@Column(name="total_amount", nullable = false)
	private BigDecimal TotalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="booking_status", nullable = false)
	private BookingStatus bookingStatus;
	
	@OneToMany(
			mappedBy="booking",
			cascade=CascadeType.ALL,
			orphanRemoval=true
			)
	private List<BookingSeat> bookingSeats=new ArrayList<>();
	
	public Booking() {
		
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public BigDecimal getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		TotalAmount = totalAmount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public List<BookingSeat> getBookingSeats() {
		return bookingSeats;
	}

	public void setBookingSeats(List<BookingSeat> bookingSeats) {
		this.bookingSeats = bookingSeats;
	}
	
	
}
