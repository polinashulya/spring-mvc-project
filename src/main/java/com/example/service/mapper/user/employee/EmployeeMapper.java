package com.example.service.mapper.user.employee;

import com.example.entity.EmployeeEntity;
import com.example.service.dto.EmployeeDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = EmployeeMapperConfig.class)
public interface EmployeeMapper extends AbstractMapper<EmployeeDto, EmployeeEntity> {

    EmployeeDto toDto(EmployeeEntity entity);

    EmployeeEntity toEntity(EmployeeDto dto);

    List<EmployeeDto> toDtoList(List<EmployeeEntity> employeeEntities);

}
