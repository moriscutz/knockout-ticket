package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateEventUseCase;
import com.knockoutticket.backend.business.exception.EventNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateEventRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepository eventRepository;

    @Override
    public UpdateEventResponse updateEvent(Long id, UpdateEventRequest request) {
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);

        eventEntity.setStatus(request.getStatus());

        EventEntity updatedEventEntity = eventRepository.save(eventEntity);

        return new UpdateEventResponse(updatedEventEntity.getId(), updatedEventEntity.getStatus());
    }
}
