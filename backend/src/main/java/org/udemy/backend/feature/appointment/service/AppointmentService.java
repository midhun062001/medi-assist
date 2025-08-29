package org.udemy.backend.feature.appointment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.udemy.backend.common.embeddable.TimeRange;
import org.udemy.backend.exception.TimeSlotNotAvailableException;
import org.udemy.backend.feature.appointment.dto.AppointmentRequestDTO;
import org.udemy.backend.feature.appointment.mapper.AppointmentMapper;
import org.udemy.backend.feature.appointment.model.Appointment;
import org.udemy.backend.feature.appointment.repository.AppointmentRepository;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.doctor.service.DoctorService;
import org.udemy.backend.feature.patient.model.Patient;
import org.udemy.backend.feature.patient.service.PatientService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    /**
     * @author MIDHUN
     *
     * Creates a new appointment for a patient with a doctor on a specified date and time.
     * <p>
     *  The method performs the following validations before saving the appointment:
     *   <ul>
     *       <li>Checks if the appointment day is part of the doctor's available working days.</li>
     *       <li>Checks if the appointment time falls within the doctor's available time slots.</li>
     *       <li>Checks if the requested time slot is already booked by another appointment.</li>
     *   </ul>
     *   Each appointment is assumed to be 15 minutes long. Booked time slots are checked to ensure
     *   no overlap with the requested slot.
     *  </p>
     *   @param appointmentDto the appointment request details, including doctorId, patientId,
     *                         and appointmentDateTime {@link AppointmentRequestDTO}
     *   @return the saved {@link Appointment} entity with a generated ID and associated doctor and patient
     *   @throws TimeSlotNotAvailableException if the doctor is not available on the selected day/time
     *                                         or if the slot is already booked
     */
    public Appointment createAppointment(AppointmentRequestDTO appointmentDto) {
        Doctor doctor = doctorService.getDoctorById(appointmentDto.getDoctorId());
        Patient patient = patientService.getPatientById(appointmentDto.getPatientId());

        LocalDate appointmentDate = appointmentDto.getAppointmentDate();
        LocalTime appointmentTime = appointmentDto.getAppointmentTime();
        DayOfWeek dayOfWeek = appointmentDate.getDayOfWeek();

        // check if day is available for doctor
        if(!doctor.getAvailableDays().contains(dayOfWeek)) {
            throw new TimeSlotNotAvailableException("Doctor's Available Days are not available");
        }

        // check if time slot is available for doctor
        boolean isInSlot = doctor.getAvailableSlots().stream()
                .anyMatch(slot ->
                        (appointmentTime.isAfter(slot.getStartTime()) || appointmentTime.equals(slot.getStartTime()))
                                && appointmentTime.isBefore(slot.getEndTime()));

        if(!isInSlot) {
            throw new TimeSlotNotAvailableException("Doctor not available at this time");
        }

        log.info("check booked time slots");
        // check if time slot is already booked
        List<Appointment> appointments = getAppointmentsByDoctorIdAndDate(doctor.getId(), appointmentDto.getAppointmentDate());

        boolean isBooked = appointments.stream()
                .anyMatch(appointment -> {
                    LocalTime startTime = appointment.getAppointmentTime();
                    LocalTime endTime = startTime.plusMinutes(15);

                    LocalTime currentStartTime = appointmentTime;
                    LocalTime currentEndTime = currentStartTime.plusMinutes(15);

                    return !(endTime.isBefore(currentStartTime.plusSeconds(1))
                            || startTime.isAfter(currentEndTime.minusSeconds(1)));
                });

        if(isBooked) {
            throw new TimeSlotNotAvailableException("Time slot has already been booked");
        }

        log.info("booked time slots");
        // save appointment
        return appointmentRepository.save(AppointmentMapper.toEntity(appointmentDto, doctor, patient));
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> getAppointmentsByDoctorIdAndDate(int doctorId, LocalDate date) {
        return appointmentRepository.findByDoctor_IdAndAppointmentDate(doctorId, date);
    }

    /**
     * @author MIDHUN
     *
     * Returns a list of available time slots (as {@link TimeRange}) for a doctor on a specific date.
     * <p>
     * The method divides each of the doctor's available time ranges into 15-minute blocks
     * and excludes those that overlap with already booked appointments.
     * <p>
     * If consecutive unbooked 15-minute blocks exist, they are merged into a single {@link TimeRange}.
     *
     * @param doctorId the ID of the doctor
     * @param date     the specific date to check availability for
     * @return a list of available {@link TimeRange}s where appointments can be booked
     */
    public List<TimeRange> getAvailableSlotsByDoctorIdAndDate(int doctorId, LocalDate date) {
        Doctor doctor = doctorService.getDoctorById(doctorId);

        List<Appointment> appointments = getAppointmentsByDoctorIdAndDate(doctorId, date);

        List<TimeRange> bookedSlots = appointments.stream()
                .map(appointment -> {
                    LocalTime startTime = appointment.getAppointmentTime();
                    return new TimeRange(startTime, startTime.plusMinutes(15) );
                })
                .toList();

        List<TimeRange> availableRanges = new ArrayList<>();

        for (TimeRange slot : doctor.getAvailableSlots()) {

            LocalTime startTime = slot.getStartTime();
            LocalTime  endTime = slot.getEndTime();

            LocalTime currentStartTime = startTime;
            TimeRange currentBlock = null;

            while(!currentStartTime.plusMinutes(15).minusSeconds(1).isAfter(endTime)) {

                LocalTime currentEndTime = currentStartTime.plusMinutes(15);
                TimeRange currentSlot = new TimeRange(currentStartTime, currentEndTime);

                boolean isBooked = bookedSlots.stream().anyMatch(booked ->
                        !(booked.getEndTime().isBefore(currentSlot.getStartTime().plusSeconds(1)) ||
                                booked.getStartTime().isAfter(currentSlot.getEndTime().minusSeconds(1)))
                );

                if (!isBooked) {
                    if(currentBlock == null) {
                        currentBlock = new TimeRange(currentStartTime, currentEndTime);
                    }
                    else {
                        currentBlock.setEndTime(currentEndTime);
                    }
                }
                else {
                    if(currentBlock != null) {
                        availableRanges.add(currentBlock);
                        currentBlock = null;
                    }
                }

                currentStartTime = currentEndTime;
            }

            // add the last remaining time range
            if(currentBlock != null) {
                availableRanges.add(currentBlock);
            }
        }

        return availableRanges;
    }
}
