package org.udemy.backend.feature.appointment.mapper;

import org.udemy.backend.feature.appointment.dto.AppointmentRequestDTO;
import org.udemy.backend.feature.appointment.dto.AppointmentResponseDTO;
import org.udemy.backend.feature.appointment.model.Appointment;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.patient.model.Patient;

public class AppointmentMapper {
    public static Appointment toEntity(AppointmentRequestDTO dto, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(dto.getAppointmentTime());
        return appointment;
    }

    public static AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setDoctorSpeciality(appointment.getDoctor().getSpeciality());
        dto.setFee(appointment.getDoctor().getFee());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setPatientName(appointment.getPatient().getFirstName());
        return dto;
    }
}
