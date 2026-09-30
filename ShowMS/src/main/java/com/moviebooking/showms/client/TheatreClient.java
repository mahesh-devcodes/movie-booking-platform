package com.moviebooking.showms.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.moviebooking.showms.dto.TheatreResponse;
import com.moviebooking.showms.exception.ScreenNotFoundException;

@Component
public class TheatreClient {
	
	private final RestClient theatreRestClient;
	
	public TheatreClient(@Qualifier("theatreRestClient") RestClient theatreRestClient) {
		
		this.theatreRestClient=theatreRestClient;
	}
	
	public TheatreResponse getScreenById(Long screenId) {
		try {
			
		return theatreRestClient.get()
				.uri("/screens/{id}",screenId)
				.retrieve()
				.body(TheatreResponse.class);
		}catch(HttpClientErrorException.NotFound ex) {
			throw new ScreenNotFoundException(
					"Screen not found with Id: "+screenId);
		}
	}
}
