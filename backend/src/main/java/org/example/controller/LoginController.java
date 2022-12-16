package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.exception.BadCookieCredentialsException;
import org.example.exception.ErrorResponse;
import org.example.exception.UserNotFoundException;
import org.example.service.LoginService;
import org.example.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> giveCookie(@CookieValue(name = "lets-pick", required = false) String cookieValue, HttpServletResponse response) {
        try {
            Utils.checkCookiesNull(cookieValue);
        } catch (UserNotFoundException exc) {
            return loginService.saveNewUser(response);
        }
        //TODO или проставлять новые куки?
        throw new BadCookieCredentialsException(cookieValue);
    }

    @ExceptionHandler(BadCookieCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCookieCredentialsException(BadCookieCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
