package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.requests.GetEventBoxersRequest;
import com.knockoutticket.backend.domain.requests.UpdateEventRequest;
import com.knockoutticket.backend.domain.responses.*;
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
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final GetEventsCountByOrganizerUseCase getEventsCountByOrganizerUseCase;

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
    @GetMapping("/{eventId}/boxers")
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

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @PutMapping("/{id}")
    public ResponseEntity<UpdateEventResponse> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest request) {
        UpdateEventResponse response = updateEventUseCase.updateEvent(id, request);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        deleteEventUseCase.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"EVENT_ORGANIZER"})
    @GetMapping("/countByOrganizer")
    public ResponseEntity<List<GetEventsCountByOrganizerResponse>> countEventsByOrganizer(){
        return new ResponseEntity<>(getEventsCountByOrganizerUseCase.countEventsByOrganizer(),HttpStatus.OK);
    }
}
