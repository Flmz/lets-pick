package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.exception.ErrorResponse;
import org.example.exception.NoSuchContentTypeException;
import org.example.service.ApplicationService;
import org.example.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.example.util.Utils.checkCookiesNull;

@Validated
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final ApplicationService applicationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> startApp(@CookieValue(name = "lets-pick", required = false) String cookieValue,
                                          @RequestParam(value = "type", required = false) String type) {
        checkCookiesNull(cookieValue);
        return applicationService.startApp(cookieValue, type);
    }

//    @PostMapping("/next") //TODO
//    @ResponseStatus(HttpStatus.OK)
//    public List<ContentResponse> nextContent(@CookieValue(name = "lets-pick") String cookieValue,
//                                             @RequestParam(value = "type", required = false) String type,
//                                             @RequestBody @Valid List<ContentResponse> watchedContent) {
//        checkCookiesNull(cookieValue);
//        return contentPublisherService.chooseNextContent(cookieValue, type, watchedContent);
//    }

    @ExceptionHandler(NoSuchContentTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlingNoSuchTypeException(NoSuchContentTypeException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlingConstraintViolationException(ConstraintViolationException exc) {
        Set<ConstraintViolation<?>> constraintViolations = exc.getConstraintViolations();

        List<String> collect = constraintViolations.stream()
                .map(violation ->
                        String.format("%s %s - %s", StreamSupport.stream(violation.getPropertyPath()
                                                .spliterator(), false)
                                        .reduce((first, second) -> second).orElse(null),
                                violation.getInvalidValue(), violation.getMessage())).toList();
        return new ErrorResponse(Utils.writeErrorMessage(collect));
    }
}
