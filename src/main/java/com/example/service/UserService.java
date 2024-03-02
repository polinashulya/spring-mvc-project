package com.example.service;

import com.example.service.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);

}
