package com.example.validation.annotatoin;

import com.example.validation.validator.HireDateAfterBirthDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = HireDateAfterBirthDateValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HireDateAfterBirthDate {
    String message() default "Hire date must be later than birth date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}