package com.moviebooking.showms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	public RestClient movieRestClient() {
		
		return RestClient.builder()
				.baseUrl("http://localhost:8081")
				.build();
	}
	
	@Bean("theatreRestClient")
	public RestClient  theatreRestClient() {
		
		return RestClient.builder()
				.baseUrl("http://localhost:8082")
				.build();
	}
}
