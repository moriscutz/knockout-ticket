package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventsByBoxerUseCase;
import com.knockoutticket.backend.persistence.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEventsByBoxerUseCaseImpl implements DeleteEventsByBoxerUseCase {
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public void deleteEventsByBoxer(Long boxerId){
        eventRepository.deleteEventsByBoxer(boxerId);
    }
}
