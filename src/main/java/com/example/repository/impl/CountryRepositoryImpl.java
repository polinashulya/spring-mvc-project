package com.example.repository.impl;

import com.example.dao.CountryDao;
import com.example.dao.impl.CountryDaoImpl;
import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final CountryDao countryDao;

//    public CountryRepositoryImpl() {
//        countryDao = new CountryDaoImpl();
//    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        try {
            return countryDao.findById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
