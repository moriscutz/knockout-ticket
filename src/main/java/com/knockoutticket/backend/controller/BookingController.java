package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateBookingUseCase;
import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateBookingResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}