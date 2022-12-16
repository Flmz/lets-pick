package org.example.exception;

public class NoSuchContentTypeException extends RuntimeException {
    public NoSuchContentTypeException() {
        super("Failed param of content type");
    }
}
