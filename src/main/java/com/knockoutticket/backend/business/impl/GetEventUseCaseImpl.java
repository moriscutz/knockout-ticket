package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetEventUseCase;
import com.knockoutticket.backend.business.exception.EventNotFoundException;
import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.responses.GetEventResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetEventUseCaseImpl implements GetEventUseCase {

    private final EventRepository eventRepository;

    public GetEventResponse getEvent(Long id) {
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Event event = EventConverter.toEventDTO(eventEntity);
        return new GetEventResponse(event);
    }

    public EventEntity createEvent(Event event, BoxerEntity boxer1, BoxerEntity boxer2, AppUserEntity organizer, BoxerEntity winner) {
        EventEntity eventEntity = EventConverter.toEventEntity(event, boxer1, boxer2, organizer, winner);
        return eventRepository.save(eventEntity);
    }

}
