package com.example.backend.airport;

public class AirportIdNotFoundException extends RuntimeException {
    public AirportIdNotFoundException() {
    }

    public AirportIdNotFoundException(String message) {
        super(message);
    }
}
