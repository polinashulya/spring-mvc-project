package com.example.service;

import com.example.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    List<UserDto> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    void add(UserDto userDto);

    void deleteById(Long userId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
