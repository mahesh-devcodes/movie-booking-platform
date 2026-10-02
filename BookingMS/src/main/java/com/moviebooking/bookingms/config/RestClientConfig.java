package com.moviebooking.bookingms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	public RestClient showRestClient() {
		
		return RestClient.builder()
				.baseUrl("http://localhost:8083")
				.build();
	}
}
