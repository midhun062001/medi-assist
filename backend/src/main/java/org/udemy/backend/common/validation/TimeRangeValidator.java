package org.udemy.backend.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.udemy.backend.common.embeddable.TimeRange;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, TimeRange> {

    @Override
    public boolean isValid(TimeRange timeRange, ConstraintValidatorContext context) {
        if (timeRange == null || timeRange.getStartTime() == null || timeRange.getEndTime() == null)
            return true; // Skip null checks here, handled by @NotNull if required

        if (timeRange.getStartTime().isAfter(timeRange.getEndTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Start time must be before end time")
                    .addPropertyNode("startTime")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
