package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.business.exception.OrganizerNotFoundException;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddEventToFightNightUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private BoxerRepository boxerRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AddEventToFightNightUseCaseImpl addEventToFightNightUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEventToFightNight_Success() {
        // Arrange
        Long eventFightNightId = 1L;
        Long boxerId1 = 2L;
        Long boxerId2 = 3L;
        Long organizerId = 4L;
        LocalDateTime eventDate = LocalDateTime.now();
        String eventStatus = "Scheduled";
        String eventPlace = "Venue";

        AddEventToFightNightRequest request = new AddEventToFightNightRequest(
                eventFightNightId, boxerId1, boxerId2, organizerId, eventDate, eventStatus, eventPlace);

        EventFightNightEntity mockEventFightNightEntity = new EventFightNightEntity();
        mockEventFightNightEntity.setId(eventFightNightId);

        BoxerEntity mockBoxer1 = new BoxerEntity();
        mockBoxer1.setId(boxerId1);
        BoxerEntity mockBoxer2 = new BoxerEntity();
        mockBoxer2.setId(boxerId2);
        AppUserEntity mockOrganizer = new AppUserEntity();
        mockOrganizer.setId(organizerId);

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(mockEventFightNightEntity));
        when(boxerRepository.findById(boxerId1)).thenReturn(Optional.of(mockBoxer1));
        when(boxerRepository.findById(boxerId2)).thenReturn(Optional.of(mockBoxer2));
        when(appUserRepository.findById(organizerId)).thenReturn(Optional.of(mockOrganizer));

        // Act
        AddEventToFightNightResponse response = addEventToFightNightUseCase.addEventToFightNight(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getEventId());
        assertEquals(mockEventFightNightEntity.getEvents().size(), 1);
        verify(eventRepository, times(1)).save(any(EventEntity.class));
        verify(eventFightNightRepository, times(1)).save(any(EventFightNightEntity.class));
    }

    @Test
    void testAddEventToFightNight_EventFightNightNotFound() {
        // Arrange
        Long nonExistentEventFightNightId = 999L;

        AddEventToFightNightRequest request = new AddEventToFightNightRequest(
                nonExistentEventFightNightId, 1L, 2L, 3L, LocalDateTime.now(), "Scheduled", "Venue");

        when(eventFightNightRepository.findById(nonExistentEventFightNightId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventFightNightNotFoundException.class,
                () -> addEventToFightNightUseCase.addEventToFightNight(request));
        verify(boxerRepository, never()).findById(anyLong());
        verify(appUserRepository, never()).findById(anyLong());
        verify(eventRepository, never()).save(any(EventEntity.class));
        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }

    @Test
    void testAddEventToFightNight_BoxerNotFound() {
        // Arrange
        Long eventFightNightId = 1L;
        Long nonExistentBoxerId = 999L;

        AddEventToFightNightRequest request = new AddEventToFightNightRequest(
                eventFightNightId, nonExistentBoxerId, 2L, 3L, LocalDateTime.now(), "Scheduled", "Venue");

        EventFightNightEntity mockEventFightNightEntity = new EventFightNightEntity();
        mockEventFightNightEntity.setId(eventFightNightId);

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(mockEventFightNightEntity));
        when(boxerRepository.findById(nonExistentBoxerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BoxerNotFoundException.class,
                () -> addEventToFightNightUseCase.addEventToFightNight(request));
        verify(appUserRepository, never()).findById(anyLong());
        verify(eventRepository, never()).save(any(EventEntity.class));
        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }

    @Test
    void testAddEventToFightNight_OrganizerNotFound() {
        // Arrange
        Long eventFightNightId = 1L;
        Long nonExistentOrganizerId = 999L;

        AddEventToFightNightRequest request = new AddEventToFightNightRequest(
                eventFightNightId, 1L, 2L, nonExistentOrganizerId, LocalDateTime.now(), "Scheduled", "Venue");

        EventFightNightEntity mockEventFightNightEntity = new EventFightNightEntity();
        mockEventFightNightEntity.setId(eventFightNightId);

        BoxerEntity mockBoxer1 = new BoxerEntity();
        mockBoxer1.setId(1L);
        BoxerEntity mockBoxer2 = new BoxerEntity();
        mockBoxer2.setId(2L);

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(mockEventFightNightEntity));
        when(boxerRepository.findById(1L)).thenReturn(Optional.of(mockBoxer1));
        when(boxerRepository.findById(2L)).thenReturn(Optional.of(mockBoxer2));
        when(appUserRepository.findById(nonExistentOrganizerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrganizerNotFoundException.class,
                () -> addEventToFightNightUseCase.addEventToFightNight(request));
        verify(eventRepository, never()).save(any(EventEntity.class));
        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }
}
