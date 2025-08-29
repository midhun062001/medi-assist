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
public class DoctorRequestDTO {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "DOB is required")
    @Past(message = "DOB must be in the past")
    private LocalDate dob;

    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;

    @NotNull(message = "Nationality is required")
    private CountryCode nationality;

    @Valid
    @NotNull(message = "Contact info is required")
    private ContactInfo contact;

    @NotNull(message = "Speciality is required")
    private Speciality speciality;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    @Min(value = 0, message = "Experience must be non-negative")
    private int experience;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotNull(message = "License expiry date is required")
    @Future(message = "License expiry date must be in the future")
    private LocalDate licenseExpiryDate;

    @NotBlank(message = "License issued by is required")
    private String licenseIssuedBy;

    @NotNull(message = "Joining date is required")
    @Past(message = "Joining date must be in the past")
    private LocalDate joiningDate;

    private List<DayOfWeek> availableDays;

    private List<@Valid TimeRange> availableSlots;

    @NotNull(message = "Department Id is required")
     private Integer departmentId;

    @NotNull(message = "Designation is required")
    private Designation designation;

    @DecimalMin(value = "0.0", inclusive = true)
    private double fee;

    @DecimalMin(value = "0.0", inclusive = true)
    private double fixedMonthlyPay;

    @Valid
    private BankAccount bankAccount;
}

