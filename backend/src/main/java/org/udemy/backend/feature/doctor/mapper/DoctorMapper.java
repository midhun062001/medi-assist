package org.udemy.backend.feature.doctor.mapper;

import org.udemy.backend.common.embeddable.BankAccount;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.exception.NotFoundException;
import org.udemy.backend.feature.doctor.dto.DoctorListDTO;
import org.udemy.backend.feature.doctor.dto.DoctorPatchDTO;
import org.udemy.backend.feature.doctor.dto.DoctorRequestDTO;
import org.udemy.backend.feature.doctor.dto.DoctorResponseDTO;
import org.udemy.backend.feature.department.model.Department;
import org.udemy.backend.feature.doctor.model.Doctor;
import org.udemy.backend.feature.department.repository.DepartmentRepository;

public class DoctorMapper {
    public static Doctor toEntity(DoctorRequestDTO dto, Department department) {
        return Doctor.builder()
                .name(dto.getName())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .bloodGroup(dto.getBloodGroup())
                .nationality(dto.getNationality())
                .contact(dto.getContact())
                .speciality(dto.getSpeciality())
                .qualification(dto.getQualification())
                .experience(dto.getExperience())
                .licenseNumber(dto.getLicenseNumber())
                .licenseExpiryDate(dto.getLicenseExpiryDate())
                .licenseIssuedBy(dto.getLicenseIssuedBy())
                .joiningDate(dto.getJoiningDate())
                .availableDays(dto.getAvailableDays())
                .availableSlots(dto.getAvailableSlots())
                .department(department)
                .designation(dto.getDesignation())
                .fee(dto.getFee())
                .fixedMonthlyPay(dto.getFixedMonthlyPay())
                .bankAccount(dto.getBankAccount())
                .build();
    }

    public static DoctorResponseDTO toResponseDTO(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .gender(doctor.getGender())
                .dob(doctor.getDob())
                .bloodGroup(doctor.getBloodGroup())
                .nationality(doctor.getNationality())
                .contact(doctor.getContact())
                .speciality(doctor.getSpeciality())
                .qualification(doctor.getQualification())
                .experience(doctor.getExperience())
                .licenseNumber(doctor.getLicenseNumber())
                .licenseExpiryDate(doctor.getLicenseExpiryDate())
                .licenseIssuedBy(doctor.getLicenseIssuedBy())
                .joiningDate(doctor.getJoiningDate())
                .availableDays(doctor.getAvailableDays())
                .availableSlots(doctor.getAvailableSlots())
                .departmentName(doctor.getDepartment().getName())
                .designation(doctor.getDesignation())
                .fee(doctor.getFee())
                .fixedMonthlyPay(doctor.getFixedMonthlyPay())
                .bankAccount(doctor.getBankAccount())
                .build();
    }

    public static DoctorListDTO toListDTO(Doctor doctor) {
        return DoctorListDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .speciality(doctor.getSpeciality())
                .departmentName(doctor.getDepartment().getName())
                .fee(doctor.getFee())
                .build();
    }

    public static void applyPatch(Doctor existing, DoctorPatchDTO dto, DepartmentRepository departmentRepo) {
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getGender() != null) existing.setGender(dto.getGender());
        if (dto.getDob() != null) existing.setDob(dto.getDob());
        if (dto.getBloodGroup() != null) existing.setBloodGroup(dto.getBloodGroup());
        if (dto.getNationality() != null) existing.setNationality(dto.getNationality());

        if (dto.getContact() != null) {
            if (existing.getContact() == null) existing.setContact(new ContactInfo());
            ContactInfo contact = existing.getContact();
            ContactInfo patchContact = dto.getContact();
            if (patchContact.getPhone() != null) contact.setPhone(patchContact.getPhone());
            if (patchContact.getEmail() != null) contact.setEmail(patchContact.getEmail());
            if (patchContact.getAddress() != null) contact.setAddress(patchContact.getAddress());
        }

        if (dto.getSpeciality() != null) existing.setSpeciality(dto.getSpeciality());
        if (dto.getQualification() != null) existing.setQualification(dto.getQualification());
        if (dto.getExperience() != null) existing.setExperience(dto.getExperience());
        if (dto.getLicenseNumber() != null) existing.setLicenseNumber(dto.getLicenseNumber());
        if (dto.getLicenseExpiryDate() != null) existing.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        if (dto.getLicenseIssuedBy() != null) existing.setLicenseIssuedBy(dto.getLicenseIssuedBy());
        if (dto.getJoiningDate() != null) existing.setJoiningDate(dto.getJoiningDate());
        if (dto.getAvailableDays() != null) existing.setAvailableDays(dto.getAvailableDays());
        if (dto.getAvailableSlots() != null) existing.setAvailableSlots(dto.getAvailableSlots());

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found with ID " + dto.getDepartmentId()));
            existing.setDepartment(department);
        }

        if (dto.getDesignation() != null) existing.setDesignation(dto.getDesignation());
        if (dto.getFee() != null) existing.setFee(dto.getFee());
        if (dto.getFixedMonthlyPay() != null) existing.setFixedMonthlyPay(dto.getFixedMonthlyPay());

        if (dto.getBankAccount() != null) {
            if (existing.getBankAccount() == null) existing.setBankAccount(new BankAccount());
            BankAccount patchBank = dto.getBankAccount();
            BankAccount bank = existing.getBankAccount();
            if (patchBank.getAccountNumber() != null) bank.setAccountNumber(patchBank.getAccountNumber());
            if (patchBank.getBankName() != null) bank.setBankName(patchBank.getBankName());
            if (patchBank.getIfscCode() != null) bank.setIfscCode(patchBank.getIfscCode());
            if (patchBank.getBranchName() != null) bank.setBranchName(patchBank.getBranchName());
        }
    }

}
