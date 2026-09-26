package com.moviebooking.theatrems.service;

import java.util.List;

import com.moviebooking.theatrems.entity.Screen;

public interface ScreenService {
	
	Screen createScreen(Screen screen);
	List<Screen>getAllScreens();
	Screen getScreenById(Long ScreenId);
	Screen updateScreen(Long ScreenId,Screen screen);
	void deleteScreen(Long ScreenId);
}
