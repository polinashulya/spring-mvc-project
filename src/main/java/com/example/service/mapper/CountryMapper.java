package com.example.service.mapper;

import com.example.entity.CountryEntity;
import com.example.service.dto.CountryDto;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public static CountryDto toDto(CountryEntity countryEntity) {

        if (countryEntity == null) {
            return null;
        }

        CountryDto countryDto = new CountryDto();
        countryDto.setName(countryEntity.getName());

        return countryDto;
    }
    public static CountryEntity toEntity(CountryDto countryDto) {
        if (countryDto == null) {
            return null;
        }

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(countryDto.getName());

        return countryEntity;
    }
}
