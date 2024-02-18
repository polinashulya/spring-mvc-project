package com.example.service.mapper.user;

import com.example.entity.*;
import com.example.service.dto.*;
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

    CountryDto countryEntityToCountryDto(CountryEntity countryEntity);
    UserRoleDto userRoleEntityToUserRoleDto(UserRoleEntity userRoleEntity);
    EmployeePositionDto employeePositionEntityToEmployeePositionDto(EmployeePositionEntity employeePosition);
    ProcedureDto procedureEntityToProcedureDto(ProcedureEntity procedureEntity);

}