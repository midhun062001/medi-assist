package org.udemy.backend.common.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String city;

    @NotEmpty(message = "District is required")
    private String district;

    @NotEmpty(message = "State is required")
    private String state;

    @NotEmpty(message = "zipCode is required")
    @Pattern(regexp = "\\d{6}", message = "Zip code must be a 6-digit number")
    private String zipCode;
}
