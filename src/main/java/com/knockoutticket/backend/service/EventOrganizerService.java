package com.knockoutticket.backend.service;

import com.knockoutticket.backend.domain.user.EventOrganizer;
import java.util.List;
import java.util.Optional;

public interface EventOrganizerService {
    EventOrganizer saveEventOrganizer(EventOrganizer eventOrganizer);
    Optional<EventOrganizer> getEventOrganizerById(Long id);
    List<EventOrganizer> getAllEventOrganizers();
    EventOrganizer updateEventOrganizer(Long id, EventOrganizer eventOrganizer);
    void deleteEventOrganizer(Long id);
}
