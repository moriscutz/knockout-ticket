package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventsForOrganizerUseCase;
import com.knockoutticket.backend.persistence.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEventsForOrganizerUseCaseImpl implements DeleteEventsForOrganizerUseCase {

    private final EventRepository eventRepository;

    @Transactional
    @Override
    public void deleteEventsForOrganizer(Long organizerId){
        eventRepository.deleteEventsForOrganizer(organizerId);
    }
}
