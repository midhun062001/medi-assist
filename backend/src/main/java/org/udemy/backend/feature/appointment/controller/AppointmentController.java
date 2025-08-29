package org.udemy.backend.feature.appointment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udemy.backend.feature.appointment.dto.AppointmentRequestDTO;
import org.udemy.backend.feature.appointment.dto.AppointmentResponseDTO;
import org.udemy.backend.feature.appointment.mapper.AppointmentMapper;
import org.udemy.backend.feature.appointment.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        return ResponseEntity.ok(AppointmentMapper.toResponseDTO(appointmentService.createAppointment(requestDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable int id) {
        return ResponseEntity.ok(AppointmentMapper.toResponseDTO(appointmentService.getAppointmentById(id)));
    }
}
