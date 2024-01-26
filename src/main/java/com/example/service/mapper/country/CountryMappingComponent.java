package com.example.service.mapper.country;

import com.example.entity.CountryEntity;
import com.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMappingComponent {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryMappingComponent(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @CountryIdToCountryEntity
    public CountryEntity map(String countryId) {
        if (countryId == null) {
            return null;
        }
        return countryRepository.findById(Long.valueOf(countryId)).orElse(null);
    }


}