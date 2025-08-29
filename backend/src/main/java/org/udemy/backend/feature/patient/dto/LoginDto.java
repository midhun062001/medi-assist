package org.udemy.backend.feature.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotEmpty
    private String username;
    @NotNull
    private String password;
}
