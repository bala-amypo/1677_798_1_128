package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("not found");
    }

    public ResourceNotFoundException(String message) {
        // tests usually check that the text "not found" is present
        super(message == null ? "not found" : message);
    }
}
