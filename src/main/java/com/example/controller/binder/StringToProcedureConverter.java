package com.example.controller.binder;

import com.example.service.dto.ProcedureDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProcedureConverter implements Converter<String, ProcedureDto> {
    @Override
    public ProcedureDto convert(String code) {
        return ProcedureDto.builder()
                .code(code)
                .build();
    }
}
