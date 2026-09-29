package com.moviebooking.showms.controller;

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

import com.moviebooking.showms.entity.Show;
import com.moviebooking.showms.service.ShowService;

@RestController
@RequestMapping("/shows")
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Show createShow(@RequestBody Show show) {
		return showService.createShow(show);
	}
	
	@GetMapping
	public List<Show> getAllShow(){
		return showService.getAllShows();
	}
	
	@GetMapping("/{showId}")
	public Show getShowById(@PathVariable Long showId) {
		return showService.getShowById(showId);
	}
	
	@PutMapping("/{showId}")
	public Show updateShow(@PathVariable Long showId,
			@RequestBody Show show) {
			return showService.updateShow(showId, show);				
		
	}
	
	@DeleteMapping("/{showId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteShow(@PathVariable Long showId) {
		 showService.deleteShow(showId);
	}
}
