package com.moviebooking.paymentms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient bookingRestClient(
			@Value("${booking.service.url}") String bookingServiceUrl) {
		
		return RestClient.builder()
				.baseUrl(bookingServiceUrl)
				.build();
	}
		
}
