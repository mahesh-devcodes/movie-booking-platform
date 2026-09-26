package com.moviebooking.theatrems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="screen")
public class Screen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="screen_id")
	private Long screenId;
	
	@Column(name="screen_name",nullable = false)
	private String screenName;
	
	@Column(name="screen_type")
	private String screenType;
	
	@Column(name="capacity",nullable = false)
	private Integer capacity;
	
	@Column(name="theatre_id",nullable = false)
	private Long theatreId;
	
	public Screen() {
		
	}		
	
	public Screen(Long screenId, String screenName, String screenType, Integer capacity, Long theatreId) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.screenType = screenType;
		this.capacity = capacity;
		this.theatreId = theatreId;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}
		
}
