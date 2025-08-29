package org.udemy.backend.feature.appointment.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class AppointmentRequestDTO {

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be in the future")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotNull(message = "Doctor ID is required")
    private int doctorId;

    @NotNull(message = "Patient ID is required")
    private int patientId;
}
