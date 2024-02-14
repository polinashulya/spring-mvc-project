package com.example.service.mapper.country;

import com.example.entity.ClientEntity;
import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.service.dto.ClientDto;
import com.example.service.dto.CountryDto;
import com.example.service.dto.UserDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper extends AbstractMapper<CountryDto, CountryEntity> {

    List<CountryDto> toDtoList(List<CountryEntity> countryEntities);

}