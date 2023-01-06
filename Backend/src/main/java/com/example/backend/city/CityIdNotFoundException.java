package com.example.backend.city;

public class CityIdNotFoundException extends RuntimeException {
    public CityIdNotFoundException() {
    }

    public CityIdNotFoundException(String message) {
        super(message);
    }
}
