package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsException extends ResponseStatusException {
    public UsernameAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "User with username already exists");
    }
}
