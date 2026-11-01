package com.moviebooking.notificationms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviebooking.notificationms.dto.NotificationRequest;
import com.moviebooking.notificationms.entity.Notification;
import com.moviebooking.notificationms.exception.NotificationNotFoundException;
import com.moviebooking.notificationms.repository.NotificationRepository;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepo;
	
	public NotificationServiceImpl(NotificationRepository notificationRepo) {
		this.notificationRepo=notificationRepo;
	}
	
	@Override
	public Notification createNotifiction(NotificationRequest request) {
		
		Notification notification=new Notification();
		
		notification.setUserId(request.getUserId());
		notification.setBookingId(request.getBookingId());
		notification.setRecipientEmail(request.getRecipientEmail());
		notification.setMessage(request.getMessage());
		notification.setNotificationType(request.getNotificationType());
		notification.setRead(0);
		
		return notificationRepo.save(notification);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notification> getAllNotifications() {
		return notificationRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Notification getNotificationById(Long notificationId) {
		return findNotification(notificationId);
	}

	

	@Override
	@Transactional(readOnly = true)
	public List<Notification> getNotificationByUserId(Long userId) {
		return notificationRepo.findByUserId(userId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Notification> getUnreadNotifications(Long userId) {
		return notificationRepo.findByUserIdAndRead(userId,0);
	}

	@Override
	public List<Notification> getNotificationByBookingId(Long bookingId) {
		return notificationRepo.findByBookingId(bookingId);
	}

	@Override
	public Notification updateNotification(Long notificationId, NotificationRequest request) {
		
		Notification notification=findNotification(notificationId);
		
		notification.setUserId(request.getUserId());
		notification.setBookingId(request.getBookingId());
		notification.setRecipientEmail(request.getRecipientEmail());
		notification.setMessage(request.getMessage());
		notification.setNotificationType(request.getNotificationType());

		return notificationRepo.save(notification);
	}

	@Override
	public Notification markAsRead(Long notificationId) {
		Notification notification=findNotification(notificationId);
		notification.setRead(1);
		
		return notificationRepo.save(notification);
	}

	@Override
	public void deleteNotification(Long notificationId) {
		Notification notification=findNotification(notificationId);
		notificationRepo.delete(notification);

	}
	
	private Notification findNotification(Long notificationId) {
		
		return notificationRepo.findById(notificationId)
				.orElseThrow(()-> new NotificationNotFoundException("Notification not found with Id: " + notificationId));
	}

	@Override
	public Page<Notification> getAllNotifications(Pageable pageable) {
		return notificationRepo.findAll(pageable);
	}

}
