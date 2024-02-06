package com.example.repository;

import com.example.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<EmployeeEntity> findAll();

    List<EmployeeEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    EmployeeEntity getById(Long id);

    Optional<EmployeeEntity> findByEmail(String email);

    void save(EmployeeEntity employee);

    void deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
    
}
