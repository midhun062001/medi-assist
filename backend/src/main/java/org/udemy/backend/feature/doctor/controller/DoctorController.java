package org.udemy.backend.feature.doctor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.udemy.backend.feature.doctor.dto.DoctorListDTO;
import org.udemy.backend.feature.doctor.dto.DoctorPatchDTO;
import org.udemy.backend.feature.doctor.dto.DoctorRequestDTO;
import org.udemy.backend.feature.doctor.dto.DoctorResponseDTO;
import org.udemy.backend.feature.doctor.mapper.DoctorMapper;
import org.udemy.backend.feature.doctor.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<List<DoctorListDTO>> getAllDoctors() {
        List<DoctorListDTO> response = doctorService.getAllDoctors()
                .stream()
                .map(DoctorMapper::toListDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable int id) {
        DoctorResponseDTO response = DoctorMapper.toResponseDTO(doctorService.getDoctorById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO dto) {
        DoctorResponseDTO response = DoctorMapper.toResponseDTO(doctorService.createDoctor(dto));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/all")
    public ResponseEntity<List<DoctorResponseDTO>> createDoctor(@RequestBody List<@Valid DoctorRequestDTO> dto) {
        List<DoctorResponseDTO> response = dto
                .stream()
                .map(doctorService::createDoctor)
                .map(DoctorMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<DoctorResponseDTO> updateDoctor( @Valid @RequestBody DoctorPatchDTO dto) {
        DoctorResponseDTO response = DoctorMapper.toResponseDTO(doctorService.patchDoctor(dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> deleteDoctor(@PathVariable int id) {
        DoctorResponseDTO response = DoctorMapper.toResponseDTO(doctorService.deleteDoctor(id));
        return ResponseEntity.ok(response);
    }
}
