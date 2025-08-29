package org.udemy.backend.feature.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.udemy.backend.feature.patient.model.Patient;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsByAadhaar(String aadhaar);
    boolean existsByPassport(String passport);

    @Query("SELECT p FROM Patient p WHERE (p.contact.email = :username OR p.contact.phone = :username) AND p.password = :password")
    Optional<Patient> getPatientByEmailOrPhoneAndPassword(@Param("username") String username,
                                                          @Param("password") String password);
}
