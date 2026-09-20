package com.moviebooking.moviems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviebooking.moviems.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
