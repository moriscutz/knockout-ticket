package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventNotFoundException;
import com.knockoutticket.backend.persistence.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteEventUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private DeleteEventUseCaseImpl deleteEventUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteEvent_ShouldDeleteSuccessfully_WhenEventExists() {
        // Arrange
        Long eventId = 1L;

        when(eventRepository.existsById(eventId)).thenReturn(true);

        // Act
        deleteEventUseCase.deleteEvent(eventId);

        // Assert
        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void deleteEvent_ShouldThrowException_WhenEventDoesNotExist() {
        // Arrange
        Long eventId = 1L;

        when(eventRepository.existsById(eventId)).thenReturn(false);

        // Act & Assert
        assertThrows(EventNotFoundException.class, () -> {
            deleteEventUseCase.deleteEvent(eventId);
        });

        verify(eventRepository, never()).deleteById(eventId);
    }
}
