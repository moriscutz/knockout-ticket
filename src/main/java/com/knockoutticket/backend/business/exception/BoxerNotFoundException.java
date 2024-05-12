package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoxerNotFoundException extends ResponseStatusException {
    public BoxerNotFoundException(Long boxerId) {
        super(HttpStatus.NOT_FOUND, "Boxer with id " + boxerId + " not found");
    }
}
