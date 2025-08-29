package org.udemy.backend.exception;

public class TimeSlotNotAvailableException extends RuntimeException {
    public TimeSlotNotAvailableException(String message) {
        super(message);
    }
    public TimeSlotNotAvailableException() {
        super("Time slot not available");
    }
}
