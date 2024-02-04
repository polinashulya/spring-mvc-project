package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "appointments")
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity extends AbstractCoreEntity {

    @ManyToOne
    @JoinColumn(name = "appointmentStatus_id")
    private AppointmentStatusEntity appointmentStatusEntity;



}
