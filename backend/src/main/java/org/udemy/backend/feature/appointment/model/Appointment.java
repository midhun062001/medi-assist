package org.udemy.backend.feature.appointment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.patient.model.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    @ManyToOne
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JsonBackReference
    private Patient patient;
}
