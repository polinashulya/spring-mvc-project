package com.example.repository.impl;

import com.example.dao.EmployeeDao;
import com.example.entity.EmployeeEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeDao employeeDao;

    @Override
    public List<EmployeeEntity> findAll() {
        try {
            return employeeDao.findAll();
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<EmployeeEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return employeeDao.findAll( search, countryId, sortBy, sortType, page, pageSize);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public EmployeeEntity getById(Long id) {
        try {
            return employeeDao.getById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(EmployeeEntity employee) {
        try {
            employeeDao.save(employee);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            employeeDao.delete(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            final String filterAndSearchSql = employeeDao.getFilterAndSearchHql(countryId, search);
            return employeeDao.getTotalResult(filterAndSearchSql);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<EmployeeEntity> findByEmail(String email) {
        try {
            return employeeDao.findByEmail(email);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}