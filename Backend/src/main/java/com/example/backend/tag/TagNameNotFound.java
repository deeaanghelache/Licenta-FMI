package com.example.backend.tag;

public class TagNameNotFound extends RuntimeException{
    public TagNameNotFound() {
    }

    public TagNameNotFound(String message) {
        super(message);
    }
}
