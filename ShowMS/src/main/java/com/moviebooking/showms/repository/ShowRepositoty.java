package com.moviebooking.showms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.showms.entity.Show;

public interface ShowRepositoty extends JpaRepository<Show, Long> {

}
