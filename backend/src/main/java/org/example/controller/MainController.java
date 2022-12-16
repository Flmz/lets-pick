package org.example.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.exception.ErrorResponse;
import org.example.exception.NoSuchContentTypeException;
import org.example.service.ContentPublisherService;
import org.example.util.Utils;
import org.example.validator.ListSizeConstraint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final ContentPublisherService contentPublisherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> startApp(@CookieValue(name = "lets-pick") String cookieValue,
                                          @RequestParam(value = "type", required = false) String type) {
        Utils.checkCookiesNull(cookieValue);
        return contentPublisherService.startApp(cookieValue, type, null);
    }

    @PostMapping("/next")
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> nextContent(@CookieValue(name = "lets-pick") String cookieValue,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestBody @ListSizeConstraint List<ContentResponse> watchedContent) {
        Utils.checkCookiesNull(cookieValue);

        return contentPublisherService.startApp(cookieValue, type, watchedContent);
    }

    @ExceptionHandler(NoSuchContentTypeException.class)
    public ResponseEntity<ErrorResponse> handlingNoSuchTypeException(NoSuchContentTypeException exception) {
        return returnErrorResponse(exception, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlingConstraintViolationException(ConstraintViolationException exc) {
        return returnErrorResponse(exc, HttpStatus.BAD_GATEWAY);
    }

    private ResponseEntity<ErrorResponse> returnErrorResponse(Exception exception, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, status);
    }
}
