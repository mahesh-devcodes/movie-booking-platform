package com.moviebooking.notificationms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.notificationms.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findByUserId(Long userId);
	List<Notification> findByUserIdAndRead(Long userId, Integer read);
	List<Notification> findByBookingId(Long bookingId);

}
