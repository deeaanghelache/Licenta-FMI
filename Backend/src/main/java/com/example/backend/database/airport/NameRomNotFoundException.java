package com.example.backend.database.airport;

public class NameRomNotFoundException extends RuntimeException{
    public NameRomNotFoundException() {
    }

    public NameRomNotFoundException(String message) {
        super(message);
    }
}
