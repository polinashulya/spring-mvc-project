package com.example.service.mapper.user.employee;

import com.example.entity.EmployeeEntity;
import com.example.service.dto.EmployeeDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(config = EmployeeMapperConfig.class)
public interface EmployeeMapper extends AbstractMapper<EmployeeDto, EmployeeEntity> {

    EmployeeEntity toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(EmployeeEntity clienEntity);


}
