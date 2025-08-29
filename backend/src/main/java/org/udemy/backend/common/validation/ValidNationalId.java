package org.udemy.backend.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AadhaarOrPassportValidator.class)
public @interface ValidNationalId {
    String message() default "Invalid Aadhaar or Passport number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
