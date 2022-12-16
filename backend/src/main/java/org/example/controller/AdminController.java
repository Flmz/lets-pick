package org.example.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.exception.ContentSaveException;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.service.ContentService;
import org.example.validator.ContentValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.util.Utils.checkCookiesNull;
import static org.example.util.Utils.writeErrorMessage;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ContentMapper mapper;
    private final ContentValidator validator;
    private final ContentService contentService;

    @PostMapping
    public ContentResponse save(@CookieValue(name = "lets-pick") String cookieValue, @RequestBody @Valid ContentResponse contentResponse,
                                BindingResult bindingResult) {

        checkCookiesNull(cookieValue);
        Content content = mapper.toEntity(contentResponse);
        validator.validate(content, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ContentSaveException(writeErrorMessage(bindingResult));
        }
        return mapper.toDTO(contentService.save(content));
    }

    @GetMapping
    public List<ContentResponse> index() {
        return mapper.toListDto(contentService.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        contentService.delete(id);
    }
}
