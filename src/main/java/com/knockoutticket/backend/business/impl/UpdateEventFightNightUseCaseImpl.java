package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.UpdateEventFightNightUseCase;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEventFightNightUseCaseImpl implements UpdateEventFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;

    @Override
    public UpdateEventFightNightResponse updateEventFightNight(Long id, UpdateEventFightNightRequest request) {
        EventFightNightEntity fightNightEntity = eventFightNightRepository.findById(id)
                .orElseThrow(() -> new EventFightNightNotFoundException(id));

        fightNightEntity.setTitle(request.getTitle());
        fightNightEntity.setDate(request.getDate());
        fightNightEntity.setStartTime(request.getStartTime());
        fightNightEntity.setEndTime(request.getEndTime());
        fightNightEntity.setPlace(request.getPlace());

        EventFightNightEntity updatedFightNightEntity = eventFightNightRepository.save(fightNightEntity);

        return new UpdateEventFightNightResponse(
                updatedFightNightEntity.getId(),
                updatedFightNightEntity.getTitle(),
                updatedFightNightEntity.getDate(),
                updatedFightNightEntity.getStartTime(),
                updatedFightNightEntity.getEndTime(),
                updatedFightNightEntity.getPlace()
        );
    }
}
