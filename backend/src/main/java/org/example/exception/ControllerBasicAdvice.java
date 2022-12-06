package org.example.exception;

import org.example.dto.ResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerBasicAdvice {

    @ExceptionHandler
    public ResponseDTO handleUserNotException(UserNotFoundException exception) {
        return new ResponseDTO(exception.getMessage(), 401, MediaType.APPLICATION_JSON);
    }
}
