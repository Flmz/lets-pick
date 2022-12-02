package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public ResponseEntity<?> giveCookie(HttpServletResponse response, HttpServletRequest request) {
        if (request.getCookies() == null) {
            return loginService.saveNewUser(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
