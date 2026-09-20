package com.moviebooking.moviems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebooking.moviems.entity.Movie;
import com.moviebooking.moviems.exception.MovieNotFoundException;
import com.moviebooking.moviems.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepo;
	
	public MovieServiceImpl(MovieRepository movieRepo) {
		this.movieRepo=movieRepo;
	}
	
	public Movie addMovie(Movie movie) { 
		return movieRepo.save(movie);
	}

	
	public List<Movie> getAllMovies() { 
		return movieRepo.findAll();
	}

	
	public Movie getMovieById(Long movieId) {
		return movieRepo.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie not found with ID: " +movieId));
	}

	
	public Movie updateMovie(Long movieId, Movie movie) {
		
		 Movie existingMovie=getMovieById(movieId);
		 existingMovie.setTitle(movie.getTitle());
		 existingMovie.setDescription(movie.getDescription());
		 existingMovie.setLanguage(movie.getLanguage());
		 existingMovie.setGenre(movie.getGenre());
		 existingMovie.setDuration(movie.getDuration());
		 existingMovie.setReleaseDate(movie.getReleaseDate());
		 existingMovie.setRating(movie.getRating());
		 
		return movieRepo.save(existingMovie);
	}

	
	public void deleteMovie(Long movieId) {
		Movie existingMovie=getMovieById(movieId);
		movieRepo.delete(existingMovie);
	}

}
