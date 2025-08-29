package org.udemy.backend.feature.patient.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.udemy.backend.exception.BadRequestException;
import org.udemy.backend.exception.NotFoundException;
import org.udemy.backend.feature.patient.dto.PatientRequestDTO;
import org.udemy.backend.feature.patient.dto.PatientResponseDTO;
import org.udemy.backend.feature.patient.dto.PatientUpdateDTO;
import org.udemy.backend.feature.patient.mapper.PatientMapper;
import org.udemy.backend.feature.patient.model.Patient;
import org.udemy.backend.feature.patient.repository.PatientRepository;

import java.util.List;
import java.util.Optional;


/**
 * Service layer for handling business logic related to Patient entities.
 * Responsible for creation, retrieval, update, and deletion operations.
 * Ensures validation, uniqueness constraints, and mapping between DTOs and entities.
 *
 * @author Midhun
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PatientService {

    /**
     * Patient JPA repository for database interactions.
     */
    private final PatientRepository patientRepository;

    /**
     * Private method to persist a Patient entity.
     *
     * @param patient the patient entity to save
     * @return the saved patient entity
     */
    private Patient savePatient(Patient patient) {return patientRepository.save(patient);}

    /**
     * Saves a single patient based on the incoming PatientRequestDTO.
     * Validates uniqueness of Aadhaar and Passport numbers before persisting.
     *
     * @param dto the request DTO containing patient information
     * @return a response DTO containing saved patient details
     * @throws BadRequestException if Aadhaar or Passport already exists
     */
    public PatientResponseDTO savePatient(PatientRequestDTO dto) {

        Patient patient = PatientMapper.toEntity(dto);

        // Check if Aadhaar is already registered
        if (dto.getAadhaar() != null) {
                if(patientRepository.existsByAadhaar(patient.getAadhaar())) {
                    throw new BadRequestException("Patient already exists with same aadhaar.");
                }
            }
        // Check if Passport is already registered
        if (patient.getPassport() != null) {
            if(patientRepository.existsByPassport(patient.getPassport())) {
                throw new BadRequestException("Patient already exists with same Passport.");
            }
        }

        return PatientMapper.toResponseDTO(savePatient(patient));
    }

    /**
     * Saves a list of patients in bulk.
     * Each patient is individually validated and mapped before being saved.
     *
     * @param dtoList the list of PatientRequestDTOs
     * @return list of PatientResponseDTOs for the saved patients
     */
    public List<PatientResponseDTO> savePatient(@Valid List<PatientRequestDTO> dtoList) {
        return dtoList.stream()
                .map(PatientMapper::toEntity)
                .map(this::savePatient)
                .map(PatientMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves all patients from the database.
     *
     * @return list of all PatientResponseDTOs
     */
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a single patient by ID.
     *
     * @param id the patient ID to search for
     * @return the patient entity
     * @throws NotFoundException if no patient with the given ID exists
     */
    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found."));
    }

    /**
     * Updates an existing patient using partial update logic.
     * Only non-null fields in the DTO are applied.
     *
     * @param patch the PatientUpdateDTO containing updated fields
     * @return the updated patient as a response DTO
     * @throws NotFoundException if patient with given ID does not exist
     */
    public PatientResponseDTO updatePatient(PatientUpdateDTO patch) {
        Patient patient = patientRepository.findById(patch.getId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));

       PatientMapper.attach(patch, patient);
       patientRepository.save(patient);
       return PatientMapper.toResponseDTO(patient);
    }

    /**
     * Deletes a patient from the database by ID.
     *
     * @param id the ID of the patient to delete
     * @return a response DTO of the deleted patient
     * @throws NotFoundException if patient is not found
     */
    public PatientResponseDTO deletePatient(@PathVariable int id) {
        Patient patient = getPatientById(id);
        if(patient == null) throw new NotFoundException("Patient not found.");
        patientRepository.delete(patient);
        return PatientMapper.toResponseDTO(patient);
    }

    public PatientResponseDTO getPatientByEmailOrPhoneAndPassword(String username, String password) {
        Optional<Patient> patient = patientRepository.getPatientByEmailOrPhoneAndPassword(username,password);
        if(patient.isEmpty()) throw new NotFoundException("Patient not found.");
        return PatientMapper.toResponseDTO(patient.get());
    }
}
