package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateEventFightNightUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @InjectMocks
    private UpdateEventFightNightUseCaseImpl updateEventFightNightUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateEventFightNight_Success() {
        // Arrange
        Long eventFightNightId = 1L;
        EventFightNightEntity mockFightNightEntity = new EventFightNightEntity();
        mockFightNightEntity.setId(eventFightNightId);
        mockFightNightEntity.setTitle("Original Title");
        mockFightNightEntity.setDate(LocalDate.of(2024, 6, 15));
        mockFightNightEntity.setStartTime(LocalTime.of(18, 0));
        mockFightNightEntity.setEndTime(LocalTime.of(21, 0));
        mockFightNightEntity.setPlace("Original Place");

        UpdateEventFightNightRequest request = new UpdateEventFightNightRequest();
        request.setTitle("Updated Title");
        request.setDate(LocalDate.of(2024, 6, 16));
        request.setStartTime(LocalTime.of(19, 0));
        request.setEndTime(LocalTime.of(22, 0));
        request.setPlace("Updated Place");

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(mockFightNightEntity));
        when(eventFightNightRepository.save(any(EventFightNightEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UpdateEventFightNightResponse response = updateEventFightNightUseCase.updateEventFightNight(eventFightNightId, request);

        // Assert
        assertNotNull(response);
        assertEquals(eventFightNightId, response.getId());
        assertEquals("Updated Title", response.getTitle());
        assertEquals(LocalDate.of(2024, 6, 16), response.getDate());
        assertEquals(LocalTime.of(19, 0), response.getStartTime());
        assertEquals(LocalTime.of(22, 0), response.getEndTime());
        assertEquals("Updated Place", response.getPlace());

        verify(eventFightNightRepository, times(1)).findById(eventFightNightId);
        verify(eventFightNightRepository, times(1)).save(mockFightNightEntity);
    }

    @Test
    void testUpdateEventFightNight_NotFound() {
        // Arrange
        Long eventFightNightId = 999L;
        UpdateEventFightNightRequest request = new UpdateEventFightNightRequest();
        request.setTitle("Updated Title");
        request.setDate(LocalDate.of(2024, 6, 16));
        request.setStartTime(LocalTime.of(19, 0));
        request.setEndTime(LocalTime.of(22, 0));
        request.setPlace("Updated Place");

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventFightNightNotFoundException.class, () -> updateEventFightNightUseCase.updateEventFightNight(eventFightNightId, request));

        verify(eventFightNightRepository, times(1)).findById(eventFightNightId);
        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }
}
