package com.moviebooking.notificationms.service;

import java.util.List;

import com.moviebooking.notificationms.dto.NotificationRequest;
import com.moviebooking.notificationms.entity.Notification;

public interface NotificationService {
	
	Notification createNotifiction(NotificationRequest request);
	List<Notification> getAllNotifications();
	Notification getNotificationById(Long notificationId);
	List<Notification> getNotificationByUserId(Long userId);
	List<Notification> getUnreadNotifications(Long userId);
	List<Notification> getNotificationByBookingId(Long bookingId);
	
	Notification updateNotification(Long notificationId,
			NotificationRequest request);
	Notification markAsRead(Long notificationId);
	
	void deleteNotification(Long notificationId);
}
