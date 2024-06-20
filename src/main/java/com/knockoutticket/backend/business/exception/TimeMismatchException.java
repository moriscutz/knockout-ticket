package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TimeMismatchException extends ResponseStatusException {
    public TimeMismatchException() {
        super(HttpStatus.BAD_REQUEST, "Event date does not match Fight Night date");
    }
}
