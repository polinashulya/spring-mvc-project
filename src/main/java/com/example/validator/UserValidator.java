package com.example.validator;

import java.sql.Date;
import java.time.LocalDate;

public interface UserValidator {

    boolean validate(String email, String password, String name, String surname,
                     LocalDate birthDate);

}
