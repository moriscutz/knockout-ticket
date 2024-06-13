package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateException extends ResponseStatusException {
    public InvalidDateException() {
        super(HttpStatus.BAD_REQUEST, "Date cannot be null, and must be a future date.");
    }
}
