package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetAllEventsUseCase;
import com.knockoutticket.backend.domain.responses.GetEventResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class    GetAllEventsUseCaseImpl implements GetAllEventsUseCase {
    private final EventRepository eventRepository;

    @Override
    public List<GetEventResponse> getAllEvents() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        return eventEntities.stream()
                .map(eventEntity -> new GetEventResponse(EventConverter.toEventDTO(eventEntity)))
                .toList();
    }
}
