package com.example.validation.annotatoin;

import com.example.validation.validator.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidAge {
    String message() default "You must be at least 16 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minAge() default 16;
}