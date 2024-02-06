package com.example.service.dto;

import com.example.entity.EmployeePositionEntity;
import com.example.entity.ProcedureEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    private Set<EmployeePositionEntity> positions = new HashSet<>();

    private Set<ProcedureEntity> procedures = new HashSet<>();

}
