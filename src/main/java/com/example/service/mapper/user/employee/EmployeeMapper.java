package com.example.service.mapper.user.employee;

import com.example.entity.EmployeeEntity;
import com.example.service.dto.EmployeeDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = EmployeeMapperConfig.class)
public interface EmployeeMapper extends AbstractMapper<EmployeeDto, EmployeeEntity> {

    @Mapping(target = "countryId", source = "country.id")
    EmployeeDto toDto(EmployeeEntity entity);

    @Mapping(target = "country", ignore = true)
    EmployeeEntity toEntity(EmployeeDto dto);

    List<EmployeeDto> toDtoList(List<EmployeeEntity> employees);

}
