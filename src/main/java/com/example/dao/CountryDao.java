package com.example.dao;

import com.example.entity.Country;
import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<Country> findAll();

    Country getById(Long id);

    Optional<Country> findById(Long id);

}
