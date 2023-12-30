package com.example.service.mapper;

import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
import com.example.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setLogin(userEntity.getLogin());
        userDto.setPassword(userEntity.getPassword());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setBirthDate(String.valueOf(userEntity.getBirthDate()));
        userDto.setBanned(userEntity.isBanned());
        userDto.setDeleted(userEntity.isDeleted());

        if (userEntity.getCountry() != null) {
            userDto.setCountryId(String.valueOf(userEntity.getCountry().getId()));
        }

        return userDto;
    }

    public static UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setBirthDate(LocalDate.parse(userDto.getBirthDate()));
        userEntity.setBanned(userDto.isBanned());
        userEntity.setDeleted(userDto.isDeleted());

        if (userDto.getCountryId() != null) {
            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setId(Long.valueOf(userDto.getCountryId()));
            userEntity.setCountry(countryEntity);
        }

        return userEntity;
    }
}