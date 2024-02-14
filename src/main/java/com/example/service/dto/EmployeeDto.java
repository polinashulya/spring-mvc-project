package com.example.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class EmployeeDto extends UserDto {

    private LocalDate hireDate;

    private Set<EmployeePositionDto> positions = new HashSet<>();

    private Set<ProcedureDto> procedures = new HashSet<>();

}
