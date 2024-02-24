package com.example.entity;


import com.example.entity.core.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employee_positions")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePositionEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
