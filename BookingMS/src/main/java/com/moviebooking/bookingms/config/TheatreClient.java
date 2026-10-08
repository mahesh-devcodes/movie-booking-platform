package com.moviebooking.bookingms.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.moviebooking.bookingms.dto.SeatResponse;

@Component
public class TheatreClient {
	
	private final RestClient theatreRestClient;
	
	public TheatreClient(
            @Qualifier("theatreRestClient") RestClient theatreRestClient) {

        this.theatreRestClient = theatreRestClient;
    }

	// Get seat details from TheatreMS
	public SeatResponse getSeatById(Long seatId) {
		
		return theatreRestClient.get()
				.uri("/seats/{id}", seatId)
				.retrieve()
				.body(SeatResponse.class);
	}
	
	// Update seat status in TheatreMS
	public SeatResponse updateSeatStatus(Long seatId, String seatStatus) {
		
		return theatreRestClient.patch()
				.uri("/seats/{id}/status",seatId)
				.body(Map.of("seatStatus", seatStatus))
				.retrieve()
				.body(SeatResponse.class);
	}
}
