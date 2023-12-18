package com.example.service.impl;

import com.example.entity.Country;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.repository.impl.CountryRepositoryImpl;
import com.example.service.CountryService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);
    private final CountryRepository countryRepository;

//    public CountryServiceImpl() {
//        countryRepository = new CountryRepositoryImpl();
//    }


    @Override
    public List<Country> findAll() {
        try {
            return countryRepository.findAll();
        } catch (DAOException e) {
            logger.error("Error while getting all countries: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
