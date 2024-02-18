package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends AbstractCoreEntity {

    @ManyToOne
    private CountryEntity country;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "registationDate")
    private LocalDate registationDate;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToMany
    @JoinTable(name = "users_userRoles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "userRole_id"))
    private Set<UserRoleEntity> userRoles = new HashSet<>();

}
