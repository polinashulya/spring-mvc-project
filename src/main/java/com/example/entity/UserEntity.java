package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Entity
@Table(name = "users")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends AbstractCoreEntity {

    @ManyToOne
    private CountryEntity country;

    @ManyToOne
    private UserRoleEntity userRole;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "deleted")
    private boolean deleted;



}
