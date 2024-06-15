package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateArchivedEventUseCase;
import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.business.exception.OrganizerNotFoundException;
import com.knockoutticket.backend.domain.requests.CreateArchivedEventRequest;
import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateArchivedEventResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.ArchivedEventRepository;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.ArchivedEventEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateArchivedEventUseCaseImpl implements CreateArchivedEventUseCase {

    private final AppUserRepository appUserRepository;
    private final BoxerRepository boxerRepository;
    private final ArchivedEventRepository archivedEventRepository;

    @Override
    public CreateArchivedEventResponse createArchivedEvent(CreateArchivedEventRequest request){
        AppUserEntity organizer = appUserRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new OrganizerNotFoundException(request.getOrganizerId()));

        BoxerEntity winner = boxerRepository.findById(request.getWinnerId())
                .orElseThrow(() -> new BoxerNotFoundException(request.getWinnerId()));

        BoxerEntity loser = boxerRepository.findById(request.getLoserId())
                .orElseThrow(() -> new BoxerNotFoundException(request.getLoserId()));

        ArchivedEventEntity archivedEvent = ArchivedEventEntity.builder()
                .organizer(organizer)
                .date(request.getDate())
                .winner(winner)
                .loser(loser)
                .place(request.getPlace())
                .build();

        archivedEvent = archivedEventRepository.save(archivedEvent);

        return CreateArchivedEventResponse.builder()
                .id(archivedEvent.getId())
                .build();

    }
}
