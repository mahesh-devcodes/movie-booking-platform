package com.moviebooking.theatrems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.theatrems.entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
