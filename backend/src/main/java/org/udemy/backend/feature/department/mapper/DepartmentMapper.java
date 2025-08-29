package org.udemy.backend.feature.department.mapper;

import org.udemy.backend.feature.department.dto.DepartmentRequestDTO;
import org.udemy.backend.feature.department.dto.DepartmentResponseDTO;
import org.udemy.backend.feature.department.model.Department;

public class DepartmentMapper {

    public static DepartmentResponseDTO toResponseDTO(Department dept) {
        if (dept == null) return null;

        return DepartmentResponseDTO.builder()
                .id(dept.getId())
                .name(dept.getName())
                .hodName(dept.getHod() != null ? dept.getHod().getName() : null)
                .build();
    }

    public static Department toEntity(DepartmentRequestDTO dto) {
        if (dto == null) return null;

        return Department.builder()
                .name(dto.getName().toUpperCase())
                .build();
    }
}
