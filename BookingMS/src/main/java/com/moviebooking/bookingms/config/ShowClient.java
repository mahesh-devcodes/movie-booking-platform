package com.moviebooking.bookingms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.moviebooking.bookingms.dto.ShowResponse;

@Component
public class ShowClient {
	
	private final RestClient showRestClient;
	
	public ShowClient(RestClient showRestClient) {
		this.showRestClient=showRestClient;
	}
	
	public ShowResponse getSHowById(Long showId) {
		
		return showRestClient.get()
				.uri("/shows/{id}",showId)
				.retrieve()
				.body(ShowResponse.class);
	}
}
