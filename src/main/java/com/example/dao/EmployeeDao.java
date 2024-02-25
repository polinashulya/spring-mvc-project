package com.example.dao;

import com.example.dao.impl.DeletionStatus;
import com.example.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    List<EmployeeEntity> findAll(String search, String countyId, String sortBy, String sortType, String page, String pageSize, String positionId, String procedureId);

    Optional<EmployeeEntity> findById(Long id);

    DeletionStatus softDelete(Long id);

    EmployeeEntity getByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);

    int getTotalResult(String search, String countryId, String positionId, String procedureId);

    void save(EmployeeEntity employee);

}
