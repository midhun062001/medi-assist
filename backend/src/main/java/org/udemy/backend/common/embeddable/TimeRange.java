package org.udemy.backend.common.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.udemy.backend.common.validation.ValidTimeRange;

import java.time.LocalTime;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ValidTimeRange
public class TimeRange {
    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;
}
