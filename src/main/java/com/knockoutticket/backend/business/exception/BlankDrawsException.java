package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankDrawsException extends ResponseStatusException {
    public BlankDrawsException() {
        super(HttpStatus.BAD_REQUEST, "Draws must not be null");
    }
}
