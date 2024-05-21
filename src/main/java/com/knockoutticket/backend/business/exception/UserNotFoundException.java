package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException () { super(HttpStatus.BAD_REQUEST, "User could not be found");}
}
