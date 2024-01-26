package com.example.service.mapper.user;

import com.example.entity.UserEntity;
import com.example.service.dto.UserDto;
import com.example.service.mapper.core.AbstractMapper;
import com.example.service.mapper.country.CountryIdToCountryEntity;
import com.example.service.mapper.country.CountryMappingComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CountryMappingComponent.class)
public interface UserMapper extends AbstractMapper<UserDto, UserEntity> {

    @Mapping(target = "country", source = "countryId", qualifiedBy = CountryIdToCountryEntity.class)
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

}