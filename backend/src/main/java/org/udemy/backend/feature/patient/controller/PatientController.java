package org.udemy.backend.feature.patient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.udemy.backend.feature.patient.dto.LoginDto;
import org.udemy.backend.feature.patient.dto.PatientRequestDTO;
import org.udemy.backend.feature.patient.dto.PatientResponseDTO;
import org.udemy.backend.feature.patient.dto.PatientUpdateDTO;
import org.udemy.backend.feature.patient.mapper.PatientMapper;
import org.udemy.backend.feature.patient.service.PatientService;

import java.util.List;

/**
 * REST controller for managing patient-related operations.
 * Handles HTTP requests related to creating, retrieving, updating, and deleting patients.
 *
 * Base URL: /patients
 *
 * @author Midhun
 * @version 1.0
 */
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:5173",
        allowCredentials = "true") // allow React app
public class PatientController {

    /**
     * Service layer dependency for business logic operations on patients.
     */
    private final PatientService patientService;

    /**
     * Creates a single patient record.
     *
     * @param dto the PatientRequestDTO containing patient information
     * @return ResponseEntity with the created PatientResponseDTO
     */
    @PostMapping("/create")
    public ResponseEntity<PatientResponseDTO> savePatient(@Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.savePatient(dto));
    }

    /**
     * Bulk creates multiple patient records.
     * For testing use only, not for production!
     * @param dtoList list of valid PatientRequestDTOs
     * @return ResponseEntity with a list of created PatientResponseDTOs
     */
    @PostMapping("/create/all")
    public ResponseEntity<List<PatientResponseDTO>> savePatient(@RequestBody List<@Valid PatientRequestDTO> dtoList) {
        return ResponseEntity.ok(patientService.savePatient(dtoList));
    }

    /**
     * Retrieves all registered patients.
     *
     * @return ResponseEntity with a list of PatientResponseDTOs
     */
    @GetMapping("/all")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("/login")
    public ResponseEntity<PatientResponseDTO> getPatientByEmailOrPhoneAndPassword(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(patientService.getPatientByEmailOrPhoneAndPassword(dto.getUsername(), dto.getPassword()));
    }

    /**
     * Retrieves a single patient by ID.
     *
     * @param id the ID of the patient
     * @return ResponseEntity with the corresponding PatientResponseDTO
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable int id) {
        return ResponseEntity.ok(PatientMapper.toResponseDTO(patientService.getPatientById(id)));
    }

    /**
     * Updates an existing patient using partial data.
     *
     * @param dto the PatientUpdateDTO containing updated fields
     * @return ResponseEntity with the updated PatientResponseDTO
     */
    @PutMapping("/update")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @RequestBody PatientUpdateDTO dto) {
        return ResponseEntity.ok( patientService.updatePatient(dto));
    }

    /**
     * Deletes a patient by ID.
     *
     * @param id the ID of the patient to delete
     * @return ResponseEntity with the deleted PatientResponseDTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok( patientService.deletePatient(id));
    }
}
