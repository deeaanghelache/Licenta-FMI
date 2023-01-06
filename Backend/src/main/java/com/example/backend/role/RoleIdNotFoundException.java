package com.example.backend.role;

public class RoleIdNotFoundException extends RuntimeException {
    public RoleIdNotFoundException() {
    }

    public RoleIdNotFoundException(String message) {
        super(message);
    }
}
