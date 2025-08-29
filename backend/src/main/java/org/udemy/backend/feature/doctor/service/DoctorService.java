package org.udemy.backend.feature.doctor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.udemy.backend.exception.NotFoundException;
import org.udemy.backend.feature.doctor.dto.DoctorPatchDTO;
import org.udemy.backend.feature.doctor.dto.DoctorRequestDTO;
import org.udemy.backend.feature.doctor.mapper.DoctorMapper;
import org.udemy.backend.feature.department.model.Department;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.department.repository.DepartmentRepository;
import org.udemy.backend.feature.doctor.repository.DoctorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    public Doctor createDoctor(DoctorRequestDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("Department not found with ID " + dto.getDepartmentId()));

        Doctor doctor = DoctorMapper.toEntity(dto, department);
        return doctorRepository.save(doctor);
    }

    public Doctor patchDoctor(DoctorPatchDTO dto) {
        Doctor existing = doctorRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Doctor not found with ID " + dto.getId()));

        DoctorMapper.applyPatch(existing, dto, departmentRepository);

        return doctorRepository.save(existing);
    }

    public Doctor deleteDoctor(int id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found with ID " + id));
        doctorRepository.deleteById(id);
        return doctor;
    }
}
