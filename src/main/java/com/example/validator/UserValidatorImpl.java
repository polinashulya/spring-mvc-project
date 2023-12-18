package com.example.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserValidatorImpl implements UserValidator {

    private static final String LOGIN_FORMAT_REGEX = "^[a-zA-Z][a-zA-Z0-9-_.]{2,19}$";

    private static final String PASSWORD_FORMAT_REGEX = ".{2,19}$";

    private static final String NAME_FORMAT_REGEX = ".{2,19}";

    @Override
    public boolean validate(String login, String password, String firstname, String surname,
                            LocalDate birthDate) {
        return login.matches(LOGIN_FORMAT_REGEX)
                && password.matches(PASSWORD_FORMAT_REGEX)
                && firstname.matches(NAME_FORMAT_REGEX)
                && surname.matches(NAME_FORMAT_REGEX)
                && birthDate.isBefore(LocalDate.now());
    }

}
