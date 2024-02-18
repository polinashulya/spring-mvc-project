package com.example.service.mapper;

import com.example.entity.EmployeePositionEntity;
import com.example.service.dto.EmployeePositionDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EmployeePositionMapper extends AbstractMapper<EmployeePositionDto, EmployeePositionEntity> {

    List<EmployeePositionDto> toDtoList(List<EmployeePositionEntity> entities);

}