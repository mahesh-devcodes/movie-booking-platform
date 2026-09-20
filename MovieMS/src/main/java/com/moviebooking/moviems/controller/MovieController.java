package com.moviebooking.moviems.controller;

import java.util.List;

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

import com.moviebooking.moviems.entity.Movie;
import com.moviebooking.moviems.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	
	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movie addMovie(@RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}
	
	@GetMapping
	public List<Movie> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{movieId}")
	public Movie getMovieById(@PathVariable Long movieId) {
		return movieService.getMovieById(movieId);
	}
	
	@PutMapping("/{movieId}")
	public Movie updateMovie(@PathVariable Long movieId,
							@RequestBody Movie movie) {
		return movieService.updateMovie(movieId, movie);
	}
	
	@DeleteMapping("/{movieId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMovie(@PathVariable Long movieId) {
		movieService.deleteMovie(movieId);
	}
}
