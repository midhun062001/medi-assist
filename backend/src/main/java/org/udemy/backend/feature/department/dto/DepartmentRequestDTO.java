package org.udemy.backend.feature.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequestDTO {
    @NotBlank(message = "Department name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s&\\-]+$", message = "Name must contain only letters, spaces, &, or -")
    private String name;
}
