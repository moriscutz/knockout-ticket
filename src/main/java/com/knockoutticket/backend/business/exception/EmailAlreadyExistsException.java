package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends ResponseStatusException {
    public EmailAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "User with email already exists");
    }
}