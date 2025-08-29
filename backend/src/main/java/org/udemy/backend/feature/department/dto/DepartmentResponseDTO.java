package org.udemy.backend.feature.department.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponseDTO {
    private int id;
    private String name;
    private String hodName;
}
