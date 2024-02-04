package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "procedureCategories")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureCategoryEntity extends AbstractCoreEntity {

    @Column(name = "name")
    private String name;

}
