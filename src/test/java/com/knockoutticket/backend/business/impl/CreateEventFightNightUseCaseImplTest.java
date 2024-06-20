package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.BlankPlaceException;
import com.knockoutticket.backend.business.exception.BlankTitleException;
import com.knockoutticket.backend.business.exception.InvalidDateException;
import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateEventFightNightUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @InjectMocks
    private CreateEventFightNightUseCaseImpl createEventFightNightUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEventFightNight_ShouldReturnResponse_WhenCreationIsSuccessful() {
        // Arrange
        CreateEventFightNightRequest request = CreateEventFightNightRequest.builder()
                .title("Fight Night")
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place("Stadium")
                .build();

        EventFightNightEntity savedEvent = EventFightNightEntity.builder()
                .id(1L)
                .title(request.getTitle())
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .place(request.getPlace())
                .build();

        when(eventFightNightRepository.save(any(EventFightNightEntity.class))).thenReturn(savedEvent);

        // Act
        CreateEventFightNightResponse response = createEventFightNightUseCase.createEventFightNight(request);

        // Assert
        assertNotNull(response);
        assertEquals(savedEvent.getId(), response.getId());
        verify(eventFightNightRepository, times(1)).save(any(EventFightNightEntity.class));
    }

    @Test
    void createEventFightNight_ShouldThrowBlankTitleException_WhenTitleIsBlank() {
        // Arrange
        CreateEventFightNightRequest request = CreateEventFightNightRequest.builder()
                .title(" ")
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place("Stadium")
                .build();

        // Act & Assert
        assertThrows(BlankTitleException.class, () -> {
            createEventFightNightUseCase.createEventFightNight(request);
        });

        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }

    @Test
    void createEventFightNight_ShouldThrowInvalidDateException_WhenDateIsInThePast() {
        // Arrange
        CreateEventFightNightRequest request = CreateEventFightNightRequest.builder()
                .title("Fight Night")
                .date(LocalDate.now().minusDays(1))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place("Stadium")
                .build();

        // Act & Assert
        assertThrows(InvalidDateException.class, () -> {
            createEventFightNightUseCase.createEventFightNight(request);
        });

        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }

    @Test
    void createEventFightNight_ShouldThrowBlankPlaceException_WhenPlaceIsBlank() {
        // Arrange
        CreateEventFightNightRequest request = CreateEventFightNightRequest.builder()
                .title("Fight Night")
                .date(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place(" ")
                .build();

        // Act & Assert
        assertThrows(BlankPlaceException.class, () -> {
            createEventFightNightUseCase.createEventFightNight(request);
        });

        verify(eventFightNightRepository, never()).save(any(EventFightNightEntity.class));
    }
}
