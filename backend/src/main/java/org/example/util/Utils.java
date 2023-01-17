package org.example.util;

import org.example.exception.UserNotFoundException;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String writeErrorMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> String
                        .format("%s - %s + .\n", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining());
    }

    public static String writeErrorMessage(List<String> errorMessages) {
        return errorMessages.stream()
                .map(message -> String.format("%s.", message))
                .collect(Collectors.joining());
    }

    public static void checkCookiesNull(String cookieName) {
        if (cookieName == null) throw new UserNotFoundException();
    }
}
