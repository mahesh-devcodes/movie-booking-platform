package com.moviebooking.moviems.service;

import java.util.List;

import com.moviebooking.moviems.entity.Movie;

public interface MovieService {

	Movie addMovie(Movie movie);
	List<Movie> getAllMovies();
	Movie getMovieById(Long movieId);
	Movie updateMovie(Long movieId, Movie movie);
	void deleteMovie(Long movieId);
	
	
}
