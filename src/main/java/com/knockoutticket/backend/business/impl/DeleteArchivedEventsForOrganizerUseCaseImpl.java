package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteArchivedEventsForOrganizerUseCase;
import com.knockoutticket.backend.persistence.ArchivedEventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteArchivedEventsForOrganizerUseCaseImpl implements DeleteArchivedEventsForOrganizerUseCase {

    private final ArchivedEventRepository archivedEventRepository;

    @Transactional
    @Override
    public void deleteArchivedEventsForOrganizer(Long organizerId) {
        archivedEventRepository.deleteArchivedEventsByOrganizerId(organizerId);
    }
}
