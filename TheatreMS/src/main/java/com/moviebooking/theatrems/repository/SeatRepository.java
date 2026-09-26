package com.moviebooking.theatrems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.theatrems.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
