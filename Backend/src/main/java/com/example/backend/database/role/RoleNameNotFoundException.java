package com.example.backend.database.role;

public class RoleNameNotFoundException extends RuntimeException {
    public RoleNameNotFoundException() {
    }

    public RoleNameNotFoundException(String message) {
        super(message);
    }
}
