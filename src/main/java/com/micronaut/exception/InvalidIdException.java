package com.micronaut.exception;

public class InvalidIdException extends Exception {
    public InvalidIdException(String message) {
        super(message);
    }
}
