package com.moviebooking.theatrems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.theatrems.entity.Screen;
import com.moviebooking.theatrems.service.ScreenService;

@RestController
@RequestMapping("/screens")
public class ScreenController {
	@Autowired
	private ScreenService screenService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Screen createScreen(@RequestBody Screen screen) {
		return screenService.createScreen(screen);
	}
	
	@GetMapping
	public List<Screen> getAllScreens(){
		return screenService.getAllScreens();
	}
	
	@GetMapping("/{screenId}")
	public Screen getScreenById(@PathVariable Long screenId) {
		return screenService.getScreenById(screenId);
	}
	
	@PutMapping("/{screenId}")
	public Screen updateScreen(@PathVariable long screenId,
							 @RequestBody Screen screen) {
	return screenService.updateScreen(screenId, screen);	
	}
	
	@DeleteMapping("/{screenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteScreen(@PathVariable long screenId) {
		screenService.deleteScreen(screenId);
	}
}
