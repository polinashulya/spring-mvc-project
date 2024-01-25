package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "contacts")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
public class ContactsEntity extends AbstractCoreEntity {

    @OneToOne
    private UserEntity user;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phoneNumber", unique = true)
    private long phoneNumber;

}
