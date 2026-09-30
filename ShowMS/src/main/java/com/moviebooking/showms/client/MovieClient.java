package com.moviebooking.showms.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.moviebooking.showms.dto.MovieResponse;
import com.moviebooking.showms.exception.MovieNotFoundException;

@Component
public class MovieClient {

	private final RestClient movieRestClient;

    public MovieClient(RestClient movieRestClient) {
        this.movieRestClient = movieRestClient;
    }
	
	public MovieResponse getMovieById(Long movieId) {
		
		try {
		
			return movieRestClient.get()
				.uri("/movies/{id}",movieId)
				.retrieve()
				.body(MovieResponse.class);
		
	}catch(HttpClientErrorException.NotFound e) {
		
		throw new MovieNotFoundException("Movie not found with Id:"+movieId);
	}
	}		
}
