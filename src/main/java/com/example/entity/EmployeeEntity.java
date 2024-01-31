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
@Table(name = "employes")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class EmployeeEntity extends UserEntity {

    @Column(name = "hireDate")
    private LocalDate hireDate;

    @ManyToMany
    @JoinTable(name = "employees_positions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")

    )
    private Set<EmployeePositionEntity> positions = new HashSet<>();

}
