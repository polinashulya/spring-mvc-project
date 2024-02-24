package com.example.validation;

import java.time.LocalDate;

public interface UserValidator {

    boolean isUserValid(String email, String password, String name, String surname,
                        LocalDate birthDate);

}
