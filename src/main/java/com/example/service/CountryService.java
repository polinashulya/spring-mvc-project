package com.example.service;

import com.example.service.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAll();

    CountryDto findById(String id);

}
