package com.example.entity;

import com.example.entity.core.AbstractCoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "name")
    private String name;

    @Column(name = "appointmentDate")
    private LocalDate appointmentDate;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private ProcedureEntity procedure;



}
