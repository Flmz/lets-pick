package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.dto.ContentDTO;
import org.example.service.PublisherService;
import org.example.util.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final PublisherService publisherService;

    @GetMapping
    public List<ContentDTO> startApp(HttpServletRequest request) {
        Utils.checkCookiesNull(request);
        return publisherService.startApp(request);
    }
}
