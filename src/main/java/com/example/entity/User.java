package com.example.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private Long id;

    private Country country;

    private String login;
    private String password;

    private String firstname;
    private String surname;

    private UserStatus userStatus;

    private LocalDate birthDate;
    private boolean banned;
    private boolean deleted;



}
