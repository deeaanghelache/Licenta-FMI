package com.example.backend.database.city;

public class CityNameRomNotFoundException extends RuntimeException {
    public CityNameRomNotFoundException() {
    }

    public CityNameRomNotFoundException(String message) {
        super(message);
    }
}
