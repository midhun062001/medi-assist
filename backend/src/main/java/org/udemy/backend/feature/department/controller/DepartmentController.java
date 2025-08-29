package org.udemy.backend.feature.department.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udemy.backend.feature.department.dto.DepartmentRequestDTO;
import org.udemy.backend.feature.department.dto.DepartmentResponseDTO;
import org.udemy.backend.feature.department.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentRequestDTO dto) {
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @PutMapping("/{departmentId}/assign-hod/{doctorId}")
    public ResponseEntity<DepartmentResponseDTO> assignHod(
            @PathVariable int departmentId,
            @PathVariable int doctorId) {
        return ResponseEntity.ok(departmentService.assignHod(departmentId, doctorId));
    }


    @GetMapping("/all")
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }
}
