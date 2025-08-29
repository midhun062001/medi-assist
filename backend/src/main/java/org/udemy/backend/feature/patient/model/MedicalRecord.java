package org.udemy.backend.feature.patient.model;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String condition;
    private String description;
    private LocalDate diagnosedOn;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
