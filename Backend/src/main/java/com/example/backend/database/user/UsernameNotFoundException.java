package com.example.backend.database.user;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
