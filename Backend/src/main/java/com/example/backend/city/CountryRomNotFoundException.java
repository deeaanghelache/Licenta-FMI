package com.example.backend.city;

public class CountryRomNotFoundException extends RuntimeException {
    public CountryRomNotFoundException() {
    }

    public CountryRomNotFoundException(String message) {
        super(message);
    }
}
