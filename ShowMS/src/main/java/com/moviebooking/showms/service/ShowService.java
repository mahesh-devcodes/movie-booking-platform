package com.moviebooking.showms.service;

import java.util.List;

import com.moviebooking.showms.entity.Show;

public interface ShowService {
	
	Show createShow(Show show);
	List<Show> getAllShows();
	Show getShowById(Long showId);
	Show updateShow (Long showId, Show show);
	void deleteShow(Long showId);
	
}
