package org.udemy.backend.feature.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.udemy.backend.common.embeddable.BankAccount;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.embeddable.TimeRange;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;
import org.udemy.backend.feature.doctor.enums.Designation;
import org.udemy.backend.feature.doctor.enums.Speciality;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Data
public class DoctorPatchDTO {

    @NotNull(message = "Doctor ID is required")
    private Integer id;

    // Optional fields for patch
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;

    private Gender gender;

    @Past(message = "DOB must be in the past")
    private LocalDate dob;

    private BloodGroup bloodGroup;

    private CountryCode nationality;

    @Valid
    private ContactInfo contact;

    private Speciality speciality;

    private String qualification;

    @Min(value = 0, message = "Experience must be non-negative")
    private Integer experience;

    private String licenseNumber;

    @Future(message = "License expiry date must be in the future")
    private LocalDate licenseExpiryDate;

    private String licenseIssuedBy;

    @Past(message = "Joining date must be in the past")
    private LocalDate joiningDate;

    private List<DayOfWeek> availableDays;

    private List<@Valid TimeRange> availableSlots;

    private Integer departmentId;

    private Designation designation;

    @DecimalMin(value = "0.0", inclusive = true, message = "Fee must be non-negative")
    private Double fee;

    @DecimalMin(value = "0.0", inclusive = true, message = "Fixed pay must be non-negative")
    private Double fixedMonthlyPay;

    @Valid
    private BankAccount bankAccount;
}
