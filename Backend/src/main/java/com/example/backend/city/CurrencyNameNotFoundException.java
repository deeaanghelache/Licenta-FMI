package com.example.backend.city;

public class CurrencyNameNotFoundException extends RuntimeException {
    public CurrencyNameNotFoundException() {
    }

    public CurrencyNameNotFoundException(String message) {
        super(message);
    }
}
