package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateEventUseCase;
import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.responses.CreateEventResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final CreateEventUseCase createEventUseCase;

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@Valid @RequestBody CreateEventRequest request)
    {
        CreateEventResponse response = createEventUseCase.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
