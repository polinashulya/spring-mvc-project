package com.example.validation.validator;

import com.example.validation.annotatoin.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {


    private static final Pattern PATTERN = Pattern.compile(com.example.validation.validator.CommonHolder.PHONE_PATTERN);

    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        if (Objects.nonNull(phoneNumber)) {
            return validatePhone(phoneNumber);
        } else {
            return false;
        }
    }

    private boolean validatePhone(final String phoneNumber) {
        Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

}

