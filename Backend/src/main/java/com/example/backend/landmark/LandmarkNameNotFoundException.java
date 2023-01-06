package com.example.backend.landmark;

public class LandmarkNameNotFoundException extends RuntimeException {
    public LandmarkNameNotFoundException() {
    }

    public LandmarkNameNotFoundException(String message) {
        super(message);
    }
}
