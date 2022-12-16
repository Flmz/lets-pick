package org.example.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSizeConstraint {
    String message() default "The input list's size should be equals 2";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
