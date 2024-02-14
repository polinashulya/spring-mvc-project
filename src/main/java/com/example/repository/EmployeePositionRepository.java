package com.example.repository;

import com.example.entity.EmployeePositionEntity;
import java.util.List;
import java.util.Optional;

public interface EmployeePositionRepository {

    List<EmployeePositionEntity> findAll();

    Optional<EmployeePositionEntity> findById(Long id);

}
