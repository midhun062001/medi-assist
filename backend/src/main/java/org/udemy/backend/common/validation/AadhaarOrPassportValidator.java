package org.udemy.backend.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.udemy.backend.common.enums.CountryCode;
import org.udemy.backend.feature.patient.dto.PatientRequestDTO;

public class AadhaarOrPassportValidator implements ConstraintValidator<ValidNationalId, PatientRequestDTO> {

    @Override
    public boolean isValid(PatientRequestDTO dto, ConstraintValidatorContext context) {

        if (dto.getNationality() == null) {
            return true;
        }

        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if (dto.getNationality() == CountryCode.IN) {
            if (dto.getAadhaar() == null) {
                context.buildConstraintViolationWithTemplate("Aadhaar is required")
                        .addPropertyNode("aadhaar")
                        .addConstraintViolation();
                isValid = false;
            }
        } else {
            if (dto.getPassport() == null) {
                context.buildConstraintViolationWithTemplate("Passport is required")
                        .addPropertyNode("passport")
                        .addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;

    }
}
