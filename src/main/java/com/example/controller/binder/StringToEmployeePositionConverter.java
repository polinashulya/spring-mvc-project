package com.example.controller.binder;

import com.example.service.dto.EmployeePositionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeePositionConverter implements Converter<String, EmployeePositionDto> {
    @Override
    public EmployeePositionDto convert(String code) {
        return EmployeePositionDto.builder()
                .code(code)
                .build();
    }
}
