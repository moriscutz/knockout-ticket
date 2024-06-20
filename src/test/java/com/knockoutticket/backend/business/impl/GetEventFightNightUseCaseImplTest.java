package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetEventFightNightUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @InjectMocks
    private GetEventFightNightUseCaseImpl getEventFightNightUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventFightNight_Success() {
        // Arrange
        Long eventFightNightId = 1L;
        EventFightNightEntity mockEventFightNightEntity = new EventFightNightEntity();
        mockEventFightNightEntity.setId(eventFightNightId);
        mockEventFightNightEntity.setTitle("Fight Night 1");
        mockEventFightNightEntity.setDate(LocalDate.of(2024, 6, 15));
        mockEventFightNightEntity.setStartTime(LocalTime.of(18, 0));
        mockEventFightNightEntity.setEndTime(LocalTime.of(21, 0));
        mockEventFightNightEntity.setPlace("Madison Square Garden");

        AppUserEntity mockOrganizer = new AppUserEntity();
        mockOrganizer.setId(10L);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1L);
        eventEntity.setOrganizer(mockOrganizer);  // Set the organizer

        mockEventFightNightEntity.setEvents(Collections.singletonList(eventEntity));

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(mockEventFightNightEntity));

        // Act
        GetEventFightNightResponse response = getEventFightNightUseCase.getEventFightNight(eventFightNightId);

        // Assert
        assertNotNull(response);
        assertEquals(eventFightNightId, response.getId());
        assertEquals("Fight Night 1", response.getTitle());
        assertEquals(LocalDate.of(2024, 6, 15), response.getDate());
        assertEquals(LocalTime.of(18, 0), response.getStartTime());
        assertEquals(LocalTime.of(21, 0), response.getEndTime());
        assertEquals("Madison Square Garden", response.getPlace());
        assertEquals(1, response.getEvents().size());
        Event event = response.getEvents().get(0);
        assertEquals(1L, event.getId());
        assertEquals(10L, event.getOrganizerId());
    }

    @Test
    void testGetEventFightNight_NotFound() {
        // Arrange
        Long eventFightNightId = 1L;
        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventFightNightNotFoundException.class,
                () -> getEventFightNightUseCase.getEventFightNight(eventFightNightId));
    }
}
