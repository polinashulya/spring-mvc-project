package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "countries")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class CountryEntity extends AbstractCoreEntity {

    @Column(name = "name", unique = true)
    private String name;

}
