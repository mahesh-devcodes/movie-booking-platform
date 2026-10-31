package com.moviebooking.notificationms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="notification")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notification_id")
	private Long notificationId;
	
	@Column(name = "user_id",nullable = false)
	@NotNull(message = "User ID is required")
	@Positive(message = "User ID must be greater than 0")
	private Long userId;
	
	@Column(name = "booking_id")
	@NotNull(message = "Booking ID is required")
	@Positive(message = "Booking ID must be greater than 0")
	private Long bookingId;
	
	@Column(name = "recipient_email",length = 150)
	@NotBlank(message = "Recipient email is required")
	@Email(message = "Please enter a valid email address")
	@Size(max = 150, message = "Email cannot exceed 150 characters")
	private String recipientEmail;
	
	@Column(name = "message",nullable = false, length = 1000)
	@NotBlank(message = "Message cannot be empty")
	@Size(max = 1000, message = "Message cannot exceed 1000 characters")
	private String message;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type",nullable = false, length = 30)
	@NotNull(message = "Notification type is required")
	private NotificationType notificationType;
	
	@Column(name = "is_read",nullable = false)
	private Integer read = 0;
	
	@Column(name = "created_at",nullable = false)
	private LocalDateTime createdAt;
	
	public Notification() {
		
	}
	
	@PrePersist
	public void prePersist() {
		if(createdAt == null) {
			createdAt = LocalDateTime.now(); 
		}
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
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

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
