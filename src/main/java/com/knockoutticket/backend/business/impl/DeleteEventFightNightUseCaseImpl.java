package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventFightNightUseCase;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEventFightNightUseCaseImpl implements DeleteEventFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;

    @Override
    public void deleteEventFightNight(Long id) {
        if (!eventFightNightRepository.existsById(id)) {
            new EventFightNightNotFoundException(id);
        }
        else {
            eventFightNightRepository.deleteById(id);
        }
    }
}
