package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "procedures")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureEntity extends AbstractCoreEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "price")
    private BigDecimal price;

}
