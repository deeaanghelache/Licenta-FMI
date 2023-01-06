package com.example.backend.user;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException() {
    }

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
