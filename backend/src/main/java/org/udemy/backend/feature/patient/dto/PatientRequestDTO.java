package org.udemy.backend.feature.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;
import org.udemy.backend.common.validation.ValidNationalId;

import java.time.LocalDate;

/**
 * Data Transfer Object for incoming patient creation requests.
 * Validates required patient information using JSR-380 (Bean Validation).
 * Custom annotation {@link ValidNationalId} validates Aadhaar or Passport rules.
 *
 * @author Midhun
 * @version 1.0
 */
@Data
@ValidNationalId
public class PatientRequestDTO {

    /**
     * First name of the patient (optional, only alphabetic characters and spaces allowed).
     */
    @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String firstName;

    /**
     * First name of the patient (optional, only alphabetic characters and spaces allowed).
     */
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String lastName;

    /**
     * Date of birth.
     * Must not be null and must be in the past.
     */
    @NotNull(message = "DOB is required")
    @Past(message = "DOB must be in the past")
    private LocalDate dob;

    /**
     * Gender of the patient (MALE, FEMALE, OTHER).
     */
    @NotNull(message = "Gender is required")
    private Gender gender;

    /**
     * Blood group (e.g., A+, O-, AB+, etc.).
     */
    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;

    /**
     * Contact information (phone, email, and address).
     * Must be valid and not null.
     */
    @Valid
    @NotNull(message = "Contact info is required")
    private ContactInfo contact;

    /**
     * Nationality as ISO country code (e.g., IN, US, UK).
     */
    @NotNull(message = "Nationality is required")
    private CountryCode nationality;

    /**
     * Optional Aadhaar number (if provided, must be 12 digits).
     */
    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be a 12-digit number")
    private String aadhaar;

    /**
     * Optional Passport number (if provided, must match standard pattern).
     * Starts with a letter followed by 6 or 7 digits.
     */
    @Pattern(regexp = "^[A-PR-WYa-pr-wy]\\d{6,7}$", message = "Invalid passport number")
    private String passport;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
}
