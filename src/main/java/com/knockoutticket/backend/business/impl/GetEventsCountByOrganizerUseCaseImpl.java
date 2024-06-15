package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetEventsCountByOrganizerUseCase;
import com.knockoutticket.backend.domain.responses.GetEventsCountByOrganizerResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventsCountByOrganizerUseCaseImpl implements GetEventsCountByOrganizerUseCase {
    private final EventRepository eventRepository;

    @Override
    public List<GetEventsCountByOrganizerResponse> countEventsByOrganizer(){
        return eventRepository.countEventsByOrganizer();
    }
}
