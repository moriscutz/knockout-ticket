package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.*;
import com.knockoutticket.backend.business.impl.CreateEventUseCaseImpl;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.responses.CreateEventResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private BoxerRepository boxerRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CreateEventUseCaseImpl createEventUseCase;

    @Test
    void createEvent_InvalidBoxer1_ThrowsBoxerNotFoundException() {
        // Arrange
        long boxerId1 = 1L;
        long boxerId2 = 2L;
        long organizerId = 3L;
        LocalDateTime now = LocalDateTime.now();
        CreateEventRequest request = new CreateEventRequest(
                boxerId1, boxerId2, organizerId, now, "EventPlace"
        );

        when(boxerRepository.findById(boxerId1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BoxerNotFoundException.class, () -> createEventUseCase.createEvent(request));
        verify(eventRepository, never()).save(any(EventEntity.class));
    }

    @Test
    void createEvent_InvalidOrganizer_ThrowsOrganizerNotFoundException() {
        // Arrange
        long boxerId1 = 1L;
        long boxerId2 = 2L;
        long organizerId = 3L;
        LocalDateTime now = LocalDateTime.now();
        CreateEventRequest request = new CreateEventRequest(
                boxerId1, boxerId2, organizerId, now, "EventPlace"
        );

        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(boxerId1);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(boxerId2);

        when(boxerRepository.findById(boxerId1)).thenReturn(Optional.of(boxer1));
        when(boxerRepository.findById(boxerId2)).thenReturn(Optional.of(boxer2));
        when(appUserRepository.findById(organizerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrganizerNotFoundException.class, () -> createEventUseCase.createEvent(request));
        verify(eventRepository, never()).save(any(EventEntity.class));
    }

    @Test
    void createEvent_Boxer1EqualsBoxer2_ThrowsEventWithSameBoxerException() {
        // Arrange
        long boxerId = 1L;
        long organizerId = 3L;
        LocalDateTime now = LocalDateTime.now();
        CreateEventRequest request = new CreateEventRequest(
                boxerId, boxerId, organizerId, now, "EventPlace"
        );

        BoxerEntity boxer = new BoxerEntity();
        boxer.setId(boxerId);

        when(boxerRepository.findById(boxerId)).thenReturn(Optional.of(boxer));
        when(appUserRepository.findById(organizerId)).thenReturn(Optional.of(new AppUserEntity()));

        // Act & Assert
        assertThrows(EventWithSameBoxerException.class, () -> createEventUseCase.createEvent(request));
        verify(eventRepository, never()).save(any(EventEntity.class));
    }

    @Test
    void createEvent_ValidRequest_SuccessfulCreation() {
        // Arrange
        long boxerId1 = 1L;
        long boxerId2 = 2L;
        long organizerId = 3L;
        LocalDateTime now = LocalDateTime.now();
        CreateEventRequest request = new CreateEventRequest(
                boxerId1, boxerId2, organizerId, now, "EventPlace"
        );

        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(boxerId1);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(boxerId2);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(organizerId);

        EventEntity savedEvent = EventEntity.builder()
                .id(1L)
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(now)
                .status(EventStatus.SCHEDULED)
                .place("EventPlace")
                .build();

        when(boxerRepository.findById(boxerId1)).thenReturn(Optional.of(boxer1));
        when(boxerRepository.findById(boxerId2)).thenReturn(Optional.of(boxer2));
        when(appUserRepository.findById(organizerId)).thenReturn(Optional.of(organizer));
        when(eventRepository.save(any(EventEntity.class))).thenReturn(savedEvent);

        // Act
        CreateEventResponse response = createEventUseCase.createEvent(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Event created successfully.", response.getMessage());

        verify(eventRepository, times(1)).save(any(EventEntity.class));
    }
}
