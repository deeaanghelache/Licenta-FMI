package com.example.backend.database.city;

public class CountryRomNotFoundException extends RuntimeException {
    public CountryRomNotFoundException() {
    }

    public CountryRomNotFoundException(String message) {
        super(message);
    }
}
