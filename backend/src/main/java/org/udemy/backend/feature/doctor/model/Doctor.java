package org.udemy.backend.feature.doctor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.udemy.backend.common.embeddable.AuditInfo;
import org.udemy.backend.common.embeddable.BankAccount;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.common.embeddable.TimeRange;
import org.udemy.backend.common.enums.BloodGroup;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.common.enums.Gender;
import org.udemy.backend.feature.appointment.model.Appointment;
import org.udemy.backend.feature.department.model.Department;
import org.udemy.backend.feature.doctor.enums.Designation;
import org.udemy.backend.feature.doctor.enums.Speciality;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"appointments", "department"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    // ---------- Primary Key ----------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ---------- Personal Info ----------
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    private CountryCode nationality;

    @Embedded
    private ContactInfo contact;

    // ---------- Professional Info ----------
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Speciality speciality;

    private String qualification;

    @Column(nullable = false)
    private int experience;

    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private String licenseIssuedBy;

    // ---------- Work Info ----------
    private LocalDate joiningDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "doctor_available_days", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "day_of_week")
    private List<DayOfWeek> availableDays;

    @ElementCollection
    @CollectionTable(name = "doctor_time_slots", joinColumns = @JoinColumn(name = "doctor_id"))
    private List<TimeRange> availableSlots;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    // ---------- Financial Info ----------
    private double fee;
    private double fixedMonthlyPay;

    @Embedded
    private BankAccount bankAccount;

    // ---------- Appointments ----------
    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

    // ---------- Audit ----------
    @Embedded
    private AuditInfo auditInfo;
}
