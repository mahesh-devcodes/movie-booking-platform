package com.moviebooking.theatrems.service;

import java.util.List;

import com.moviebooking.theatrems.entity.Theatre;

public interface TheatreService {

    Theatre createTheatre(Theatre theatre);

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long theatreId);

    Theatre updateTheatre(Long theatreId, Theatre theatre);

    void deleteTheatre(Long theatreId);
}