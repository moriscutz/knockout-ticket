package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateEventUseCase;
import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.business.exception.OrganizerNotFoundException;
import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.responses.CreateEventResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.domain.models.EventStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepository eventRepository;
    private final BoxerRepository boxerRepository;
    private final AppUserRepository appUserRepository;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public CreateEventResponse createEvent(CreateEventRequest request){
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
                .status(EventStatus.SCHEDULED)
                .place(request.getPlace())
                .build();

        EventEntity savedEvent = eventRepository.save(newEvent);

        return CreateEventResponse.builder()
                .message("Event created successfully.")
                .id(savedEvent.getId())
                .build();
    }
}
