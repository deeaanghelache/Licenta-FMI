package com.example.backend.preference;

public class PreferenceIdNotFoundException extends RuntimeException {
    public PreferenceIdNotFoundException() {
    }

    public PreferenceIdNotFoundException(String message) {
        super(message);
    }
}
