package com.moviebooking.theatrems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebooking.theatrems.entity.Theatre;
import com.moviebooking.theatrems.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long theatreId) {
        return theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    @Override
    public Theatre updateTheatre(Long theatreId, Theatre theatre) {

        Theatre existingTheatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        existingTheatre.setName(theatre.getName());
        existingTheatre.setCity(theatre.getCity());
        existingTheatre.setAddress(theatre.getAddress());

        return theatreRepository.save(existingTheatre);
    }

    @Override
    public void deleteTheatre(Long theatreId) {

        Theatre existingTheatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        theatreRepository.delete(existingTheatre);
    }
}