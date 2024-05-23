package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankLossesException extends ResponseStatusException {
    public BlankLossesException() {
        super(HttpStatus.BAD_REQUEST, "Losses must not be null");
    }
}