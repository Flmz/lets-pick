package org.example.validator;

import lombok.AllArgsConstructor;
import org.example.model.Content;
import org.example.service.ContentService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class ContentValidator implements Validator {

    private final ContentService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Content.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Content content = (Content) target;

        if (service.findByUrl(content.getUrl()).isPresent()) {
            errors.rejectValue("url", "", "This content already in db");
        }
    }
}
