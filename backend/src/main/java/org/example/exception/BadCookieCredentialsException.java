package org.example.exception;

public class BadCookieCredentialsException extends RuntimeException {
    public BadCookieCredentialsException(String cookiesName) {
        super(String.format("User with this cookies %s not registered", cookiesName));
    }
}
