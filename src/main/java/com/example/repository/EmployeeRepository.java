package com.example.repository;

import com.example.entity.DeletionStatus;
import com.example.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<EmployeeEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize, String positionId, String procedureId);

    Optional<EmployeeEntity> findById(Long id);

    Optional<EmployeeEntity> findByEmail(String email);

    void save(EmployeeEntity employee);

    DeletionStatus deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search, String positionId, String procedureId);
    
}
