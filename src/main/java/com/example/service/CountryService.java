package com.example.service;

import com.example.entity.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<Country> findAll();
}
