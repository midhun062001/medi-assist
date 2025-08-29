package org.udemy.backend.feature.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.udemy.backend.feature.doctor.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
