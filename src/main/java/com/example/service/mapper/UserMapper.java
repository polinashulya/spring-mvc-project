package com.example.service.mapper;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.exception.MapperException;
import com.example.exception.ServiceException;
import com.example.service.dto.UserDto;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Component
public class UserMapper {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final ModelMapper modelMapper;
    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  UserDto toDto(UserEntity entity) {
        try {
            return modelMapper.map(entity, UserDto.class);
        } catch (MapperException e) {
            logger.error("Error while mapping UserEntity to UserDto: {}", e.getMessage(), e);
            throw new MapperException(e);
        }
    }

    public  UserEntity toEntity(UserDto dto) {
        try {
            return modelMapper.map(dto, UserEntity.class);
        } catch (MapperException e) {
            logger.error("Error while mapping UserDto to UserEntity: {}", e.getMessage(), e);
            throw new MapperException(e);
        }
    }
}

