package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends ResponseStatusException {
    public EventNotFoundException() { super(HttpStatus.NOT_FOUND, "Event not found."); }
}
