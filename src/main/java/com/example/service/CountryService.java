package com.example.service;

import com.example.entity.CountryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryService {

    List<CountryEntity> findAll();
}
