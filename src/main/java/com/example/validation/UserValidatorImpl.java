package com.example.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserValidatorImpl implements UserValidator {

    private static final String LOGIN_FORMAT_REGEX = "^[a-zA-Z][a-zA-Z0-9-_.]{2,19}$";

    private static final String PASSWORD_FORMAT_REGEX = ".{2,19}$";

    private static final String NAME_FORMAT_REGEX = ".{2,19}";

    @Override
    public boolean isUserValid(String email, String password, String name, String surname,
                               LocalDate birthDate) {
        return email.matches(LOGIN_FORMAT_REGEX)
                && password.matches(PASSWORD_FORMAT_REGEX)
                && name.matches(NAME_FORMAT_REGEX)
                && surname.matches(NAME_FORMAT_REGEX)
                && birthDate.isBefore(LocalDate.now());
    }

}
