package com.example.backend.database.preference;

public class PreferenceIdNotFoundException extends RuntimeException {
    public PreferenceIdNotFoundException() {
    }

    public PreferenceIdNotFoundException(String message) {
        super(message);
    }
}
