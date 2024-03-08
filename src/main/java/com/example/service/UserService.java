package com.example.service;

import com.example.service.dto.UserDto;
import com.example.service.dto.UserSignUpDTO;

public interface UserService {

    String signUpUser(UserSignUpDTO signUpRequest);
    UserDto findByEmail(String email);

}
