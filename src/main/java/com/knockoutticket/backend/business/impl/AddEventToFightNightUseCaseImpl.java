package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.AddEventToFightNightUseCase;
import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.business.exception.OrganizerNotFoundException;
import com.knockoutticket.backend.business.exception.TimeMismatchException;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddEventToFightNightUseCaseImpl implements AddEventToFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;
    private final EventRepository eventRepository;
    private final BoxerRepository boxerRepository;
    private final AppUserRepository appUserRepository;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public AddEventToFightNightResponse addEventToFightNight(AddEventToFightNightRequest request) {
        EventFightNightEntity eventFightNightEntity = eventFightNightRepository.findById(request.getEventFightNightId())
                .orElseThrow(() -> new EventFightNightNotFoundException(request.getEventFightNightId()));

        BoxerEntity boxer1 = boxerRepository.findById(request.getBoxerId1())
                .orElseThrow(() -> new BoxerNotFoundException(request.getBoxerId1()));

        BoxerEntity boxer2 = boxerRepository.findById(request.getBoxerId2())
                .orElseThrow(() -> new BoxerNotFoundException(request.getBoxerId2()));

        AppUserEntity organizer = appUserRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new OrganizerNotFoundException(request.getOrganizerId()));

        EventEntity newEvent = EventEntity.builder()
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(request.getDate())
                .status(EventStatus.valueOf(request.getStatus().toUpperCase()))
                .place(request.getPlace())
                .build();

        newEvent = eventRepository.save(newEvent);

        eventFightNightEntity.getEvents().add(newEvent);
        eventFightNightRepository.save(eventFightNightEntity);

        return new AddEventToFightNightResponse(newEvent.getId());
    }
}