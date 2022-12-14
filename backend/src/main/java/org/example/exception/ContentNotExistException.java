package org.example.exception;

public class ContentNotExistException extends RuntimeException {
    public ContentNotExistException() {
        super("Content not found");
    }
}
