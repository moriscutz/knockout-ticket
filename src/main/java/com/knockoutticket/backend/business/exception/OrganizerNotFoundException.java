package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganizerNotFoundException extends ResponseStatusException {
    public OrganizerNotFoundException(Long organizerId) {
        super(HttpStatus.NOT_FOUND, "Organizer with id " + organizerId + " not found");
    }
}
