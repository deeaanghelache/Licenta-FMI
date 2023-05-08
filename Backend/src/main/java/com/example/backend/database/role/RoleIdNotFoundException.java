package com.example.backend.database.role;

public class RoleIdNotFoundException extends RuntimeException {
    public RoleIdNotFoundException() {
    }

    public RoleIdNotFoundException(String message) {
        super(message);
    }
}
