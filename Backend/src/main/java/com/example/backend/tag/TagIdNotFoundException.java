package com.example.backend.tag;

public class TagIdNotFoundException extends RuntimeException {
    public TagIdNotFoundException() {
    }

    public TagIdNotFoundException(String message) {
        super(message);
    }
}
