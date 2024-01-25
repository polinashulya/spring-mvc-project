package com.example.service.mapper;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.exception.MapperException;
import com.example.service.dto.CountryDto;
import com.example.service.dto.UserDto;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final ModelMapper modelMapper;
    @Autowired
    public CountryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CountryDto toDto(CountryEntity entity) {
        try {
            return modelMapper.map(entity, CountryDto.class);
        } catch (MapperException e) {
            logger.error("Error while mapping CountryEntity to CountryDto: {}", e.getMessage(), e);
            throw new MapperException(e);
        }
    }

    public  CountryEntity toEntity(CountryDto dto) {
        try {
            return modelMapper.map(dto, CountryEntity.class);
        } catch (MapperException e) {
            logger.error("Error while mapping CountryDto to CountryEntity: {}", e.getMessage(), e);
            throw new MapperException(e);
        }
    }
}
