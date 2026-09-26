package com.moviebooking.theatrems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.theatrems.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

}
