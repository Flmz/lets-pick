package org.example.validator;

import org.example.dto.ContentResponse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MaxSizeConstraintValidator implements ConstraintValidator<ListSizeConstraint, List<ContentResponse>> {

    @Override
    public boolean isValid(List<ContentResponse> values, ConstraintValidatorContext context) {
        return values.size() == 2;
    }
}