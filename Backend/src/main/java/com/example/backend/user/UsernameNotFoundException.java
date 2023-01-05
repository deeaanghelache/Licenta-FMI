package com.example.backend.user;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
