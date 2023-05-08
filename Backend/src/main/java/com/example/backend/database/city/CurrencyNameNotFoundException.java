package com.example.backend.database.city;

public class CurrencyNameNotFoundException extends RuntimeException {
    public CurrencyNameNotFoundException() {
    }

    public CurrencyNameNotFoundException(String message) {
        super(message);
    }
}
