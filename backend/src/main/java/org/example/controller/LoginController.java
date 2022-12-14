package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.exception.BadCookieCredentialsException;
import org.example.exception.ErrorResponse;
import org.example.exception.UserNotFoundException;
import org.example.service.LoginService;
import org.example.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.util.Utils.getCookieUUID;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> giveCookie(HttpServletResponse response, HttpServletRequest request) {
        try {
            Utils.checkCookiesNull(request);
        } catch (UserNotFoundException exc) {
            return loginService.saveNewUser(response);
        }
        //TODO или проставлять новые куки?
        throw new BadCookieCredentialsException(getCookieUUID(request));
    }

    @ExceptionHandler(BadCookieCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCookieCredentialsException(BadCookieCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
