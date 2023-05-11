package com.example.backend.database.city;

public class CityIdNotFoundException extends RuntimeException {
    public CityIdNotFoundException() {
    }

    public CityIdNotFoundException(String message) {
        super(message);
    }
}
