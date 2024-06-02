package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class DeleteBoxerUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private DeleteBoxerUseCaseImpl deleteBoxerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteBoxer_whenBoxerExists_deletesBoxerAndRelatedEvents() {
        Long boxerId = 1L;
        BoxerEntity boxerEntity = new BoxerEntity();
        boxerEntity.setId(boxerId);

        EventEntity eventEntity = new EventEntity();
        List<EventEntity> events = Collections.singletonList(eventEntity);

        when(boxerRepository.findById(boxerId)).thenReturn(Optional.of(boxerEntity));
        when(eventRepository.findByBoxer1IdOrBoxer2Id(boxerId, boxerId)).thenReturn(events);

        deleteBoxerUseCase.deleteBoxer(boxerId);

        verify(eventRepository, times(1)).deleteAll(events);
        verify(boxerRepository, times(1)).delete(boxerEntity);
    }

    @Test
    void deleteBoxer_whenBoxerDoesNotExist_throwsException() {
        Long boxerId = 1L;

        when(boxerRepository.findById(boxerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> deleteBoxerUseCase.deleteBoxer(boxerId));
        assertEquals("Boxer not found", exception.getMessage());

        verify(eventRepository, never()).findByBoxer1IdOrBoxer2Id(anyLong(), anyLong());
        verify(eventRepository, never()).deleteAll(anyList());
        verify(boxerRepository, never()).delete(any(BoxerEntity.class));
    }
}
