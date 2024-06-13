package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.*;
import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventFightNights")
@AllArgsConstructor
public class EventFightNightController {

    private final GetEventFightNightUseCase getEventFightNightUseCase;
    private final CreateEventFightNightUseCase createEventFightNightUseCase;
    private final GetAllEventFightNightsUseCase getAllEventFightNightsUseCase;
    private final AddEventToFightNightUseCase addEventToFightNightUseCase;
    private final UpdateEventFightNightUseCase updateEventFightNightUseCase;
    private final DeleteEventFightNightUseCase deleteEventFightNightUseCase;

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PostMapping
    public ResponseEntity<CreateEventFightNightResponse> createEventFightNight(@RequestBody CreateEventFightNightRequest request) {
        CreateEventFightNightResponse response = createEventFightNightUseCase.createEventFightNight(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<GetEventFightNightResponse> getEventFightNight(@PathVariable Long id) {
        GetEventFightNightResponse response = getEventFightNightUseCase.getEventFightNight(id);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER", "NORMAL_USER"})
    @GetMapping
    public ResponseEntity<List<GetEventFightNightResponse>> getAllEventFightNights() {
        List<GetEventFightNightResponse> response = getAllEventFightNightsUseCase.getAllEventFightNights();
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PostMapping("/{id}/addEvent")
    public ResponseEntity<AddEventToFightNightResponse> addEventToFightNight(@RequestBody AddEventToFightNightRequest request) {
        AddEventToFightNightResponse response = addEventToFightNightUseCase.addEventToFightNight(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @PutMapping("/{id}")
    public ResponseEntity<UpdateEventFightNightResponse> updateEventFightNight(@PathVariable Long id, @RequestBody UpdateEventFightNightRequest request) {
        UpdateEventFightNightResponse response = updateEventFightNightUseCase.updateEventFightNight(id, request);
        return ResponseEntity.ok(response);
    }

    @RolesAllowed({"ADMINISTRATOR", "EVENT_ORGANIZER"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventFightNight(@PathVariable Long id) {
        deleteEventFightNightUseCase.deleteEventFightNight(id);
        return ResponseEntity.noContent().build();
    }
}
