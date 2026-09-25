package com.moviebooking.theatrems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.theatrems.entity.Theatre;
import com.moviebooking.theatrems.service.TheatreService;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreService.createTheatre(theatre);
    }

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{theatreId}")
    public Theatre getTheatreById(@PathVariable Long theatreId) {
        return theatreService.getTheatreById(theatreId);
    }

    @PutMapping("/{theatreId}")
    public Theatre updateTheatre(
            @PathVariable Long theatreId,
            @RequestBody Theatre theatre) {

        return theatreService.updateTheatre(theatreId, theatre);
    }

    @DeleteMapping("/{theatreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheatre(@PathVariable Long theatreId) {
        theatreService.deleteTheatre(theatreId);
    }
}