package com.example.dao;

import com.example.entity.CountryEntity;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<CountryEntity> findAll();

    Optional<CountryEntity> findById(Long id);

}
