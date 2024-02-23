package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employees")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class EmployeeEntity extends UserEntity {

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToMany
    @JoinTable(name = "employees_positions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_code"))
    private Set<EmployeePositionEntity> positions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "employees_procedures",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    private Set<ProcedureEntity> procedures = new HashSet<>();

}
