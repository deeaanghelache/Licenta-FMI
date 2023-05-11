package com.example.backend.database.user;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException() {
    }

    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
