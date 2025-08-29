package org.udemy.backend.exception;

public class RedundantRecordException extends RuntimeException {
    public RedundantRecordException(String message) {
        super(message);
    }
}
