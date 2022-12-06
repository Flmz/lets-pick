package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.MainDTO;
import org.example.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.util.Utils.getCookieUUID;
import static org.example.util.Utils.writeErrorMessage;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping
    public ResponseEntity<?> startApp(@RequestBody @Valid MainDTO main, BindingResult bindingResult,
                                      HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            writeErrorMessage(bindingResult);
        }

        mainService.startApp(getCookieUUID(request), main);
        return null;
    }
}
