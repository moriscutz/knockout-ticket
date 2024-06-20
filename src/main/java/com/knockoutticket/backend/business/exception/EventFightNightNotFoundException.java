package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventFightNightNotFoundException extends ResponseStatusException {

    public EventFightNightNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Event Fight Night with the id " + id + " not found");
    }
}
