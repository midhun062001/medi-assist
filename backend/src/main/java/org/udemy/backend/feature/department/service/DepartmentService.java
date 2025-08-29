package org.udemy.backend.feature.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.udemy.backend.exception.NotFoundException;
import org.udemy.backend.exception.RedundantRecordException;
import org.udemy.backend.feature.department.dto.DepartmentRequestDTO;
import org.udemy.backend.feature.department.dto.DepartmentResponseDTO;
import org.udemy.backend.feature.department.mapper.DepartmentMapper;
import org.udemy.backend.feature.department.model.Department;
import org.udemy.backend.feature.department.repository.DepartmentRepository;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.doctor.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        Department department = DepartmentMapper.toEntity(dto);
        if (departmentRepository.existsByName(department.getName())) {
            throw new RedundantRecordException("Department with name " + department.getName() + " already exists");
        }
        departmentRepository.save(department);
        return DepartmentMapper.toResponseDTO(department);
    }

    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public DepartmentResponseDTO getById(int id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + id));
        return DepartmentMapper.toResponseDTO(dept);
    }

    public DepartmentResponseDTO assignHod(int departmentId, int doctorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        department.setHod(doctor);
        departmentRepository.save(department);

        return DepartmentMapper.toResponseDTO(department);
    }

}
