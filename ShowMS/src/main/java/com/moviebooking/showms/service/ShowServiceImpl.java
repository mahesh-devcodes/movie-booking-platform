package com.moviebooking.showms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.showms.entity.Show;
import com.moviebooking.showms.exception.ShowNotFoundException;
import com.moviebooking.showms.repository.ShowRepositoty;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowRepositoty showRepo;
	
	@Override
	public Show createShow(Show show) {
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
		Show existingShow=getShowById(showId);
		
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
