package com.example.service.mapper.user.employee;

import com.example.entity.EmployeeEntity;
import com.example.service.dto.EmployeeDto;
import com.example.service.mapper.core.AbstractMapper;
import com.example.service.mapper.user.UserMapper;
import org.mapstruct.MapperConfig;

@MapperConfig(uses = UserMapper.class)
public interface EmployeeMapperConfig extends AbstractMapper<EmployeeDto, EmployeeEntity> {

}
