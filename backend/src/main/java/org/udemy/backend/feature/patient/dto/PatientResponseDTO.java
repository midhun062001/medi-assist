package org.udemy.backend.feature.patient.dto;

import lombok.Data;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;
import org.udemy.backend.feature.appointment.model.Appointment;
import org.udemy.backend.feature.patient.model.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

@Data
public class PatientResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private BloodGroup bloodGroup;
    private ContactInfo contact;
    private CountryCode nationality;
    private List<Appointment> appointments;
    private List<MedicalRecord> medicalRecords;
}
