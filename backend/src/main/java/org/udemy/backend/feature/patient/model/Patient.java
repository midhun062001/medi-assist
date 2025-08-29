package org.udemy.backend.feature.patient.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.udemy.backend.common.converter.EncryptedStringConverter;
import org.udemy.backend.common.embeddable.Address;
import org.udemy.backend.common.embeddable.AuditInfo;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;
import org.udemy.backend.common.validation.ValidNationalId;
import org.udemy.backend.feature.appointment.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Patient entity in the medical assistant system.
 * This entity stores personal details, contact information, national IDs, audit data,
 * and maintains relationships with appointments and medical records.
 *
 * @author Midhun
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    /**
     * Embedded contact information (phone, email, address).
     */
    @Embedded
    private ContactInfo contact;

    @Enumerated(EnumType.STRING)
    private CountryCode nationality; // e.g., "IN", "US", "UK"

    /**
     * Encrypted Aadhaar number (stored securely).
     */
    @Convert(converter = EncryptedStringConverter.class)
    private String aadhaar;

    /**
     * Encrypted passport number (stored securely).
     */
    @Convert(converter = EncryptedStringConverter.class)
    private String passport;

    private String password;


    /**
     * List of appointments associated with the patient.
     * Mapped by the 'patient' field in Appointment entity.
     * Cascade operations and orphan removal enabled.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Appointment> appointments = new ArrayList<>();

    /**
     * List of medical records associated with the patient.
     * Mapped by the 'patient' field in MedicalRecord entity.
     * Cascade operations and orphan removal enabled.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Embedded
    private AuditInfo auditInfo;
}
