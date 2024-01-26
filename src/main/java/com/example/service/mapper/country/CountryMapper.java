package com.example.service.mapper.country;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.service.dto.CountryDto;
import com.example.service.dto.UserDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CountryMapper extends AbstractMapper<CountryDto, CountryEntity> {

    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

}