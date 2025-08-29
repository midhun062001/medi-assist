package org.udemy.backend.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface ValidTimeRange {
    String message() default "Invalid time range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
