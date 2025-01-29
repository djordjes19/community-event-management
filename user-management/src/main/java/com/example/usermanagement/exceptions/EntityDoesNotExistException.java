package com.example.usermanagement.exceptions;


public class EntityDoesNotExistException extends RuntimeException {
    private String message;

    public EntityDoesNotExistException(String s) {
    }
}
