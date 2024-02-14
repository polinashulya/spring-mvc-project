package com.example.service.mapper.core;

import com.example.entity.core.AbstractBaseEntity;
import com.example.service.dto.core.AbstractBaseDto;

public interface AbstractMapper<D extends AbstractBaseDto, E extends AbstractBaseEntity> {

    D toDto(E entity);

    E toEntity(D dto);

}
