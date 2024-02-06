package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clients")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClientEntity extends UserEntity {

    @Column(name = "note")
    private String note;

}
