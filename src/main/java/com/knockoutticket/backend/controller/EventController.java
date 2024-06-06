package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateEventUseCase;
import com.knockoutticket.backend.business.GetAllEventsUseCase;
import com.knockoutticket.backend.business.GetEventBoxersUseCase;
import com.knockoutticket.backend.business.GetEventUseCase;
import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.requests.GetEventBoxersRequest;
import com.knockoutticket.backend.domain.responses.CreateEventResponse;
import com.knockoutticket.backend.domain.responses.GetEventBoxersResponse;
import com.knockoutticket.backend.domain.responses.GetEventResponse;
import io.swagger.models.Response;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final CreateEventUseCase createEventUseCase;
    private final GetAllEventsUseCase getAllEventsUseCase;
    private final GetEventBoxersUseCase getEventBoxersUseCase;
    private final GetEventUseCase getEventUseCase;

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@Valid @RequestBody CreateEventRequest request)
    {
        CreateEventResponse response = createEventUseCase.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @GetMapping
    public ResponseEntity<List<GetEventResponse>> getAllEvents(){
        List<GetEventResponse> events = getAllEventsUseCase.getAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @PostMapping("/{eventId}/boxers")
    public ResponseEntity<GetEventBoxersResponse> getEventBoxers(@PathVariable Long eventId){
        GetEventBoxersRequest request = GetEventBoxersRequest.builder()
                .eventId(eventId)
                .build();

        GetEventBoxersResponse response = getEventBoxersUseCase.getEventBoxers(request);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<GetEventResponse> getEvent(@PathVariable Long id) {
        GetEventResponse eventResponse = getEventUseCase.getEvent(id);
        return ResponseEntity.ok(eventResponse);
    }
}
