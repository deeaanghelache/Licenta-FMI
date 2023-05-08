package com.example.backend.database.user;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException() {
    }

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
