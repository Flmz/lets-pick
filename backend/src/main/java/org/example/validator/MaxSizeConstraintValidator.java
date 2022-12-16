package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.dto.ContentResponse;

import java.util.List;

public class MaxSizeConstraintValidator implements ConstraintValidator<ListSizeConstraint, List<ContentResponse>> {

    @Override
    public boolean isValid(List<ContentResponse> values, ConstraintValidatorContext context) {
        return values.size() == 2;
    }
}