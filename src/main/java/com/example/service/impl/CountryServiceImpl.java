package com.example.service.impl;

import com.example.entity.CountryEntity;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.service.CountryService;
import com.example.service.dto.CountryDto;
import com.example.service.mapper.CountryMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> findAll() {
        try {
            List<CountryEntity> entities = countryRepository.findAll();
            return entities.stream()
                    .map(countryMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Error while getting all countries: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
