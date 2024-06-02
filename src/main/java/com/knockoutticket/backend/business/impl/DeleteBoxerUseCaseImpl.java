package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteBoxerUseCase;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeleteBoxerUseCaseImpl implements DeleteBoxerUseCase {
    private final BoxerRepository boxerRepository;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public void deleteBoxer(Long id) {
        BoxerEntity boxer = boxerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boxer not found"));


        List<EventEntity> events = eventRepository.findByBoxer1IdOrBoxer2Id(id, id);
        eventRepository.deleteAll(events);


        boxerRepository.delete(boxer);
    }
}
