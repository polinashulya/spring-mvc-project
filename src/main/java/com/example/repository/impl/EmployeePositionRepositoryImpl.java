package com.example.repository.impl;

import com.example.dao.EmployeePositionDao;
import com.example.entity.EmployeePositionEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.EmployeePositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeePositionRepositoryImpl implements EmployeePositionRepository {

    private final EmployeePositionDao employeePositionDao;

    @Override
    public List<EmployeePositionEntity> findAll() {
        return employeePositionDao.findAll();
    }

    @Override
    public Optional<EmployeePositionEntity> findById(Long id) {
        try {
            return employeePositionDao.findById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
