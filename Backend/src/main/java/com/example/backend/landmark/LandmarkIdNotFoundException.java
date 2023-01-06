package com.example.backend.landmark;

public class LandmarkIdNotFoundException extends RuntimeException {
    public LandmarkIdNotFoundException() {
    }

    public LandmarkIdNotFoundException(String message) {
        super(message);
    }
}
