package com.example.repository.impl;

import com.example.dao.CountryDao;
import com.example.entity.CountryEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private  CountryDao countryDao;

    @Override
    public List<CountryEntity> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Optional<CountryEntity> findById(Long id) {
        try {
            return countryDao.findById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
