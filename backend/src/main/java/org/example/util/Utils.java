package org.example.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.exception.UserNotFoundException;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class Utils {

    public static String writeErrorMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + "-" + fieldError.getDefaultMessage() + ".\n")
                .collect(Collectors.joining());
    }

    public static void checkCookiesNull(HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new UserNotFoundException();
        }
    }

    public static String getCookieUUID(HttpServletRequest request) {
        return request.getCookies()[0].getName();
    }
}
