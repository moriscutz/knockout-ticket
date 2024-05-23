package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankAgeException extends ResponseStatusException {
    public BlankAgeException() {
        super(HttpStatus.BAD_REQUEST, "Age must not be null");
    }
}