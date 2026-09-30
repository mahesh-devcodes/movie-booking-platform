package com.moviebooking.showms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebooking.showms.client.MovieClient;
import com.moviebooking.showms.client.TheatreClient;
import com.moviebooking.showms.dto.TheatreResponse;
import com.moviebooking.showms.entity.Show;
import com.moviebooking.showms.exception.ShowNotFoundException;
import com.moviebooking.showms.repository.ShowRepositoty;

@Service
public class ShowServiceImpl implements ShowService {
	
	private final MovieClient movieClient;
	private final TheatreClient theatreClient;
	private final ShowRepositoty showRepo;
	
	public ShowServiceImpl(ShowRepositoty showRepo,
			MovieClient movieClient,
			TheatreClient theatreClient) {
		this.showRepo=showRepo;
		this.movieClient=movieClient;
		this.theatreClient=theatreClient;
		
	}
	
	private void validateScreen(Long screenId) {

	    TheatreResponse screen = theatreClient.getScreenById(screenId);
	    if (screen == null || screen.getScreenId() == null) {

	        throw new RuntimeException(
	                "Screen not found in TheatreMS: " + screenId);
	    }
	}
	
	@Override
	public Show createShow(Show show) {
		 // Check whether the movie exists in MovieMS
		movieClient.getMovieById(show.getMovieId());
		// Check whether the screen exists in TheatreMS
		validateScreen(show.getScreenId());
		
		// Save show only after successful movie validation
		return showRepo.save(show);
	}

	@Override
	public List<Show> getAllShows() {
		return showRepo.findAll();
	}

	@Override
	public Show getShowById(Long showId) {
		return showRepo.findById(showId)
				.orElseThrow(()->
				new ShowNotFoundException("Show not found with ID:" +showId));
	}

	@Override
	public Show updateShow(Long showId, Show show) {
		// Get existing Show from database
		Show existingShow=getShowById(showId);
		
		// Validate the new movie ID through MovieMS
		movieClient.getMovieById(show.getMovieId());
		
		//Validate the new Screen ID through TheatreMS
		validateScreen(show.getScreenId());
		
		existingShow.setMovieId(show.getMovieId());
		existingShow.setScreenId(show.getScreenId());
		existingShow.setShowDate(show.getShowDate());
		existingShow.setStartTime(show.getStartTime());
		existingShow.setEndTime(show.getEndTime());
		
		return showRepo.save(existingShow);
	}

	@Override
	public void deleteShow(Long showId) {
		Show existingShow=getShowById(showId);
		showRepo.delete(existingShow);

	}

}
