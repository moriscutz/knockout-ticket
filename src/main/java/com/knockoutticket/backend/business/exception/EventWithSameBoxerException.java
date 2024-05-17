package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventWithSameBoxerException extends ResponseStatusException {
    public EventWithSameBoxerException() {super(HttpStatus.BAD_REQUEST, "You cannot create an event in which one fighter fights against himself."); }
}
