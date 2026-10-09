package com.moviebooking.paymentms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.paymentms.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findByBookingId(Long bookingId);

	boolean existsByBookingId(Long bookingId);

}
