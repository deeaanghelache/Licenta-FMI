package com.example.backend.database.airport;

public class AirportIdNotFoundException extends RuntimeException {
    public AirportIdNotFoundException() {
    }

    public AirportIdNotFoundException(String message) {
        super(message);
    }
}
