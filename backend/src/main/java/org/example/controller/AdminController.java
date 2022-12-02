package org.example.controller;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.config.SwaggerConfig;
import org.example.dto.ContentDTO;
import org.example.exception.ContentSaveException;
import org.example.mapper.ContentMapper;
import org.example.model.Content;
import org.example.service.ContentService;
import org.example.validator.ContentValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.util.Utils.writeErrorMessage;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
@Api(tags = {SwaggerConfig.ADMIN_TAG})
public class AdminController {
    private final ContentMapper mapper;
    private final ContentValidator validator;
    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<?> saveContent(@RequestBody @Valid ContentDTO contentDTO, BindingResult bindingResult) {
        Content content = mapper.toEntity(contentDTO);
        validator.validate(content, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ContentSaveException(writeErrorMessage(bindingResult));
        }
        return contentService.save(content);
    }
}
