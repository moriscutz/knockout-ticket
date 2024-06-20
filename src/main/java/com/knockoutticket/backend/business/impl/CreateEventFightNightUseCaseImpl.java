package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateEventFightNightUseCase;
import com.knockoutticket.backend.business.exception.BlankPlaceException;
import com.knockoutticket.backend.business.exception.BlankTitleException;
import com.knockoutticket.backend.business.exception.InvalidDateException;
import com.knockoutticket.backend.business.exception.InvalidTimeException;
import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CreateEventFightNightUseCaseImpl implements CreateEventFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public CreateEventFightNightResponse createEventFightNight(CreateEventFightNightRequest request) {
        validateRequest(request);
        EventFightNightEntity newEventFightNight = saveNewEventFightNight(request);
        return new CreateEventFightNightResponse(newEventFightNight.getId());
    }

    private void validateRequest(CreateEventFightNightRequest request) {
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new BlankTitleException();
        }
        if (request.getDate() == null || request.getDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException();
        }


        if(request.getPlace().isBlank() || request.getTitle() == null){
            throw new BlankPlaceException();
        }
    }

    private EventFightNightEntity saveNewEventFightNight(CreateEventFightNightRequest request) {
        EventFightNightEntity newEvent = EventFightNightEntity.builder()
                .title(request.getTitle())
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .place(request.getPlace())
                .build();
        return eventFightNightRepository.save(newEvent);
    }
}