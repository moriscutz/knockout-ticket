package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventNotFoundException;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.requests.UpdateEventRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateEventUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private UpdateEventUseCaseImpl updateEventUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateEvent_Success() {
        // Arrange
        Long eventId = 1L;
        EventEntity mockEventEntity = new EventEntity();
        mockEventEntity.setId(eventId);
        mockEventEntity.setStatus(EventStatus.SCHEDULED);

        UpdateEventRequest request = new UpdateEventRequest();
        request.setStatus(EventStatus.COMPLETE);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(mockEventEntity));
        when(eventRepository.save(any(EventEntity.class))).thenAnswer(invocation -> {
            EventEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(eventId);
            return savedEntity;
        });

        // Act
        UpdateEventResponse response = updateEventUseCase.updateEvent(eventId, request);

        // Assert
        assertNotNull(response);
        assertEquals(eventId, response.getId());
        assertEquals(EventStatus.COMPLETE, response.getStatus());

        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(mockEventEntity);
    }

    @Test
    void testUpdateEvent_NotFound() {
        // Arrange
        Long eventId = 999L;
        UpdateEventRequest request = new UpdateEventRequest();
        request.setStatus(EventStatus.COMPLETE);

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventNotFoundException.class, () -> updateEventUseCase.updateEvent(eventId, request));

        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, never()).save(any(EventEntity.class));
    }
}
