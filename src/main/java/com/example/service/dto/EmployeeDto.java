package com.example.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class EmployeeDto extends UserDto {

    private LocalDate hireDate;

    private List<EmployeePositionDto> positions = new ArrayList<>();

    private List<ProcedureDto> procedures;

}
