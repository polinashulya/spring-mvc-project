package com.example.service.mapper;

import com.example.entity.ProcedureEntity;
import com.example.service.dto.ProcedureDto;
import com.example.service.mapper.core.AbstractMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProcedureMapper extends AbstractMapper<ProcedureDto, ProcedureEntity> {

    List<ProcedureDto> toDtoList(List<ProcedureEntity> entities);

}