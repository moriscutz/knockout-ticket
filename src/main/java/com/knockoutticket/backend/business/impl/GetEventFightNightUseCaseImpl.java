package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetEventFightNightUseCase;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEventFightNightUseCaseImpl implements GetEventFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;

    @Override
    public GetEventFightNightResponse getEventFightNight(Long id) {
        Optional<EventFightNightEntity> eventFightNight = eventFightNightRepository.findById(id);
        return eventFightNight.map(this::convertToResponse)
                .orElseThrow(() -> new EventFightNightNotFoundException(id));
    }

    private GetEventFightNightResponse convertToResponse(EventFightNightEntity entity) {
        List<Event> events = entity.getEvents().stream()
                .map(EventConverter::toEventDTO)
                .toList();

        return new GetEventFightNightResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getPlace(),
                events
        );
    }
}
