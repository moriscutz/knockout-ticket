package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.domain.user.EventOrganizer;
import com.knockoutticket.backend.service.EventOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-organizers")
public class EventOrganizerController {

    private final EventOrganizerService eventOrganizerService;

    @Autowired
    public EventOrganizerController(EventOrganizerService eventOrganizerService) {
        this.eventOrganizerService = eventOrganizerService;
    }

    @GetMapping
    public ResponseEntity<List<EventOrganizer>> getAllEventOrganizers() {
        List<EventOrganizer> eventOrganizers = eventOrganizerService.getAllEventOrganizers();
        return new ResponseEntity<>(eventOrganizers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventOrganizer> getEventOrganizerById(@PathVariable Long id) {
        EventOrganizer eventOrganizer = eventOrganizerService.getEventOrganizerById(id)
                .orElse(null);
        if (eventOrganizer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventOrganizer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventOrganizer> createEventOrganizer(@RequestBody EventOrganizer eventOrganizer) {
        EventOrganizer createdEventOrganizer = eventOrganizerService.saveEventOrganizer(eventOrganizer);
        return new ResponseEntity<>(createdEventOrganizer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventOrganizer> updateEventOrganizer(@PathVariable Long id, @RequestBody EventOrganizer eventOrganizer) {
        EventOrganizer updatedEventOrganizer = eventOrganizerService.updateEventOrganizer(id, eventOrganizer);
        return new ResponseEntity<>(updatedEventOrganizer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventOrganizer(@PathVariable Long id) {
        eventOrganizerService.deleteEventOrganizer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
