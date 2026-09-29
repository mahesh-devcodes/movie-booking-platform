package com.moviebooking.showms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="show")
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="show_id")
	private Long showId;
	
	@Column(name="movie_id",nullable=false)
	private Long movieId;
	
	@Column(name="screen_id",nullable=false)
	private Long screenId;
	
	@Column(name="show_date",nullable=false)
	private LocalDate showDate;
	
	@Column(name="start_date",nullable=false)
	private LocalDateTime startTime;
	
	@Column(name="end_date",nullable=false)
	private LocalDateTime endTime;
	
	public Show() {
		
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
		
}
