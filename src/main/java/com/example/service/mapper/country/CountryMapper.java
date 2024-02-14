package com.example.service.mapper.country;

import com.example.entity.CountryEntity;
import com.example.service.dto.CountryDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface CountryMapper extends AbstractMapper<CountryDto, CountryEntity> {

    List<CountryDto> toDtoList(List<CountryEntity> countryEntities);

}