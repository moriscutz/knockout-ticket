package com.knockoutticket.backend.service.impl;

import com.knockoutticket.backend.domain.user.EventOrganizer;
import com.knockoutticket.backend.repository.EventOrganizerRepository;
import com.knockoutticket.backend.service.EventOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventOrganizerServiceImpl implements EventOrganizerService {
    private final EventOrganizerRepository eventOrganizerRepository;

    @Autowired
    public EventOrganizerServiceImpl(EventOrganizerRepository eventOrganizerRepository) {
        this.eventOrganizerRepository = eventOrganizerRepository;
    }

    @Override
    public EventOrganizer saveEventOrganizer(EventOrganizer eventOrganizer) {
        return eventOrganizerRepository.save(eventOrganizer);
    }

    @Override
    public Optional<EventOrganizer> getEventOrganizerById(Long id) {
        return eventOrganizerRepository.findById(id);
    }

    @Override
    public List<EventOrganizer> getAllEventOrganizers() {
        return eventOrganizerRepository.findAll();
    }

    @Override
    public EventOrganizer updateEventOrganizer(Long id, EventOrganizer eventOrganizer) {
        return eventOrganizerRepository.findById(id)
                .map(existingEventOrganizer -> {
                    existingEventOrganizer.setOrganizationName(eventOrganizer.getOrganizationName());
                    return eventOrganizerRepository.save(existingEventOrganizer);
                }).orElseThrow(() -> new RuntimeException("Event organizer not found"));
    }

    @Override
    public void deleteEventOrganizer(Long id) {
        eventOrganizerRepository.deleteById(id);
    }
}
