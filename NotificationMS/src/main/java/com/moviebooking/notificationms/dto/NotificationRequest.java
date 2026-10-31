package com.moviebooking.notificationms.dto;

import com.moviebooking.notificationms.entity.NotificationType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class NotificationRequest {
	
	@NotNull(message = "User Id is required")
	@Positive(message = "User Id must be positive")
	private Long userId;
	
	@NotNull(message = "Booking Id is required")
	@Positive(message = "Booking Id must be positive")
	private Long bookingId;
	
	@NotBlank(message = "Recipient email is required")
	@Email(message = "Enter a valid email address")
	@Size(max = 150, message = "Email cannot exceed 150 characters")
	private String recipientEmail;
	
	@NotBlank(message = "Message is required")
	@Size(max = 1000, message = "Message cannot exceed 1000 characters")
	private String message;
	
	@NotNull(message = "Notification type is required")
	private NotificationType notificationType;
	
	public NotificationRequest() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
}
