package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TwoBoxersRequiredException extends ResponseStatusException {
    public TwoBoxersRequiredException() {super(HttpStatus.NOT_FOUND, "Events must have exactly two boxers."); }
}
