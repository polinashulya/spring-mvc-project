package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;

import java.util.List;

public class PageableDto<D extends AbstractBaseDto> {

    private List<D> elements;

    private Integer size;
}
