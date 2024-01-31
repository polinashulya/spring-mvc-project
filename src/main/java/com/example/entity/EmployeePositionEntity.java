package com.example.entity;


import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "positions")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePositionEntity extends AbstractCoreEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
