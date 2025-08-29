package org.udemy.backend.feature.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.udemy.backend.feature.appointment.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    public List<Appointment> findByDoctor_IdAndAppointmentDate(int id, LocalDate date);
}
