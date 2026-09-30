package com.moviebooking.theatrems.exception;

public class TheatreNotFoundException extends RuntimeException {

    public TheatreNotFoundException(String message) {
        super(message);
    }
}