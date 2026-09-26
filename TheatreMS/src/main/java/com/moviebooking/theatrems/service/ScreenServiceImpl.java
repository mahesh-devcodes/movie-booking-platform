package com.moviebooking.theatrems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.theatrems.entity.Screen;
import com.moviebooking.theatrems.repository.ScreenRepository;

@Service
public class ScreenServiceImpl implements ScreenService {
	
	@Autowired
	private ScreenRepository screenRepo;
	
	@Override
	public Screen createScreen(Screen screen) {
		return screenRepo.save(screen);
	}

	@Override
	public List<Screen> getAllScreens() {
		return screenRepo.findAll();
	}

	@Override
	public Screen getScreenById(Long screenId) {
		return screenRepo.findById(screenId)
				.orElseThrow(()-> new RuntimeException("Screen not found with ID:"+screenId));
	}

	@Override
	public Screen updateScreen(Long screenId, Screen screen) {
		Screen existingScreen=screenRepo.findById(screenId)
				.orElseThrow(()->new RuntimeException("Screen not found with ID:"+screenId));
		
		existingScreen.setScreenName(screen.getScreenName());
		existingScreen.setScreenType(screen.getScreenType());
		existingScreen.setCapacity(screen.getCapacity());
		existingScreen.setTheatreId(screen.getTheatreId());
		
		return screenRepo.save(existingScreen);
	}

	@Override
	public void deleteScreen(Long screenId) {
		Screen existingScreen=screenRepo.findById(screenId)
				.orElseThrow(()->new RuntimeException("Screen not found with ID:" +screenId));
		screenRepo.delete(existingScreen);
	}

}
