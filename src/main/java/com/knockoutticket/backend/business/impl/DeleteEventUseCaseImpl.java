package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventUseCase;
import com.knockoutticket.backend.business.exception.EventNotFoundException;
import com.knockoutticket.backend.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepository eventRepository;

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
             new EventNotFoundException();
        }
        eventRepository.deleteById(id);
    }
}
