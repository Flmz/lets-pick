package org.example.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.ContentResponse;
import org.example.exception.ContentSaveException;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.service.ContentService;
import org.example.validator.ContentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.example.util.Utils.checkCookiesNull;
import static org.example.util.Utils.writeErrorMessage;

@Tag(name = "Админский контроллер", description = "CRUD для контента")
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ContentMapper mapper;
    private final ContentValidator validator;
    private final ContentService contentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentResponse save(@CookieValue(name = "lets-pick") String cookieValue, @RequestBody @Valid ContentResponse contentResponse,
                                BindingResult bindingResult) {

        checkCookiesNull(cookieValue);
        validator.validate(contentResponse, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ContentSaveException(writeErrorMessage(bindingResult));
        }

        Content content = mapper.INSTANCE.toEntity(contentResponse);

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
