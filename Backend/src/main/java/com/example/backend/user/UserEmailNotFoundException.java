package com.example.backend.user;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException() {
    }

    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
