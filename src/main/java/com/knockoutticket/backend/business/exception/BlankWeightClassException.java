package com.knockoutticket.backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankWeightClassException extends ResponseStatusException {
    public BlankWeightClassException() { super(HttpStatus.BAD_REQUEST, "WeightClass must not be null");}
}