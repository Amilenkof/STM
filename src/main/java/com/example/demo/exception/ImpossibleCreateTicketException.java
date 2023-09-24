package com.example.demo.exception;

import java.time.temporal.TemporalUnit;

public class ImpossibleCreateTicketException extends RuntimeException {
    public ImpossibleCreateTicketException(String message) {
        super(message);
    }
}
