package com.example.backend.database.landmark;

public class LandmarkIdNotFoundException extends RuntimeException {
    public LandmarkIdNotFoundException() {
    }

    public LandmarkIdNotFoundException(String message) {
        super(message);
    }
}
