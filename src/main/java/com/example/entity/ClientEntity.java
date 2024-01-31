package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class ClientEntity extends UserEntity {
}
