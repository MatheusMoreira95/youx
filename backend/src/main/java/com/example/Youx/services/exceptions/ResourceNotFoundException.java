package com.example.Youx.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {

        super("Resource not found. CPF: " + id);
    }
}
