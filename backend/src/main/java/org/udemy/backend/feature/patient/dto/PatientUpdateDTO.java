package org.udemy.backend.feature.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;

import java.time.LocalDate;

@Data
public class PatientUpdateDTO {
    private int id;
    @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String firstName;

    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String lastName;

    private LocalDate dob;

    private Gender gender;

    private BloodGroup bloodGroup;

    @Valid
    private ContactInfo contact;

    private CountryCode nationality;

    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be a 12-digit number")
    private String aadhaar;

    @Pattern(regexp = "^[A-PR-WYa-pr-wy][1-9]\\d{6}$", message = "Invalid passport number")
    private String passport;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;

}
