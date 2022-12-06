package org.example.exception;

public class CannotParseVideoIdException extends RuntimeException {
    public CannotParseVideoIdException(String message) {
        super(message);
    }
}
