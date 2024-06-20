package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteEventFightNightUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private DeleteEventFightNightUseCaseImpl deleteEventFightNightUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteEventFightNight_ShouldDeleteSuccessfully_WhenEventExists() {
        // Arrange
        Long eventFightNightId = 1L;

        when(eventFightNightRepository.existsById(eventFightNightId)).thenReturn(true);

        // Act
        deleteEventFightNightUseCase.deleteEventFightNight(eventFightNightId);

        // Assert
        verify(eventFightNightRepository, times(1)).deleteAllEventsByFightNightId(eventFightNightId);
        verify(bookingRepository, times(1)).deleteAllBookingsByFightNightId(eventFightNightId);
        verify(eventFightNightRepository, times(1)).deleteById(eventFightNightId);
    }

    @Test
    void deleteEventFightNight_ShouldThrowException_WhenEventDoesNotExist() {
        // Arrange
        Long eventFightNightId = 1L;

        when(eventFightNightRepository.existsById(eventFightNightId)).thenReturn(false);

        // Act & Assert
        assertThrows(EventFightNightNotFoundException.class, () -> {
            deleteEventFightNightUseCase.deleteEventFightNight(eventFightNightId);
        });

        verify(eventFightNightRepository, never()).deleteAllEventsByFightNightId(eventFightNightId);
        verify(bookingRepository, never()).deleteAllBookingsByFightNightId(eventFightNightId);
        verify(eventFightNightRepository, never()).deleteById(eventFightNightId);
    }
}
