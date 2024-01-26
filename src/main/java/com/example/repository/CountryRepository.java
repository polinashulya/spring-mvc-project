package com.example.repository;

import com.example.entity.CountryEntity;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    List<CountryEntity> findAll();

    Optional<CountryEntity> findById(Long id);

    Optional<CountryEntity> findByName(String name);

}
