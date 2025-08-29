package org.udemy.backend.feature.doctor.dto;

import lombok.Builder;
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
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DoctorResponseDTO {
    private int id;
    private String name;
    private Gender gender;
    private LocalDate dob;
    private BloodGroup bloodGroup;
    private CountryCode nationality;
    private ContactInfo contact;
    private Speciality speciality;
    private String qualification;
    private int experience;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private String licenseIssuedBy;
    private LocalDate joiningDate;
    private List<DayOfWeek> availableDays;
    private List<TimeRange> availableSlots;
    private String departmentName;
    private Designation designation;
    private double fee;
    private double fixedMonthlyPay;
    private BankAccount bankAccount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

