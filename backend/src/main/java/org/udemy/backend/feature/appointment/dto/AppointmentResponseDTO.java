package org.udemy.backend.feature.appointment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.udemy.backend.feature.doctor.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentResponseDTO {
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private int doctorId;
    private String doctorName;
    private Speciality doctorSpeciality;
    private double fee;
    private int patientId;
    private String patientName;
}
