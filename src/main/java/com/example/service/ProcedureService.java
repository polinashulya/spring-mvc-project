package com.example.service;

import com.example.service.dto.ProcedureDto;

import java.util.List;

public interface ProcedureService {

    List<ProcedureDto> findAll();

    ProcedureDto findById(String id);

}
