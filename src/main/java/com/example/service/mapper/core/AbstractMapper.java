package com.example.service.mapper.core;

import com.example.entity.core.AbstractBaseBean;
import com.example.service.dto.core.AbstractBaseDto;

import java.util.List;

public interface AbstractMapper<D extends AbstractBaseDto, E extends AbstractBaseBean> {

    D toDto(E entity);

    E toEntity(D dto);

}
