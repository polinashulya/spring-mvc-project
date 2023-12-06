package com.example.validator;

import java.sql.Date;
import java.time.LocalDate;

public interface UserValidator {

    boolean validate(String login, String password, String firstname, String surname,
                     LocalDate birthDate);

}
