package com.example.repository;

import com.example.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    List<Country> findAll();

    Optional<Country> findById(Long id);

}
