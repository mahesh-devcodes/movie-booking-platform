package com.moviebooking.notificationms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.notificationms.dto.NotificationRequest;
import com.moviebooking.notificationms.entity.Notification;
import com.moviebooking.notificationms.service.NotificationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/notifications")
@Validated
public class NotificationController {
	
	private final NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService=notificationService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Notification createNotification(
			@Valid @RequestBody NotificationRequest request) {
		
		return notificationService.createNotifiction(request);
	}
	
	@GetMapping
	public List<Notification> getAllNotifications(){
		return notificationService.getAllNotifications();
	}
	
	@GetMapping("/{notificationId}")
	public Notification getNotificationById(@PathVariable @Positive Long notificationId) {
	
		return notificationService.getNotificationById(notificationId);
	}
	
	@GetMapping("/user/{userId}")
	public List<Notification> getNotificationsByUserId(@PathVariable @Positive Long userId){
		
		return notificationService.getNotificationByUserId(userId);
	}
	
	@GetMapping("/user/{userId}/unread")
	public List<Notification> getUnreadNotifications(@PathVariable @Positive Long userId){
		
		return notificationService.getUnreadNotifications(userId);
	}
	
	@GetMapping("/booking/{bookingId}")
	public List<Notification> getNotificationsByBookingId(
			@PathVariable @Positive Long bookingId){
		return notificationService.getNotificationByBookingId(bookingId);
	}
	
	@PutMapping("/{notificationId}")
	public Notification updateNotification(
			@PathVariable @Positive Long notificationId,
			@Valid @RequestBody NotificationRequest request) {
		return notificationService.updateNotification(notificationId, request);
	}
	
	@PatchMapping("/{notificationId}/read")
	public Notification markAsRead(
			@PathVariable @Positive Long notificationId) {
		
		return notificationService.markAsRead(notificationId);
	}
	
	@DeleteMapping("/{notificationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNotification(
			@PathVariable @Positive Long notificationId) {
		notificationService.deleteNotification(notificationId);
	}
}
