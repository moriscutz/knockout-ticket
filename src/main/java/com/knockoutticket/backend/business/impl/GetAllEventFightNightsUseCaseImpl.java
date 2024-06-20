package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetAllEventFightNightsUseCase;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllEventFightNightsUseCaseImpl implements GetAllEventFightNightsUseCase {

    private final EventFightNightRepository eventFightNightRepository;

    @Override
    public List<GetEventFightNightResponse> getAllEventFightNights(){
        List<EventFightNightEntity> eventFightNights = eventFightNightRepository.findAll();
        return eventFightNights.stream()
                .map(EventFightNightConverter::toEventFightNightDTO)
                .map(dto -> new GetEventFightNightResponse(dto.getId(), dto.getTitle(), dto.getDate(), dto.getStartTime(), dto.getEndTime(), dto.getPlace(), dto.getEvents()))
                .toList();
    }
}
