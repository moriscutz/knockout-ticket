package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.responses.GetEventResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllEventsUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private GetAllEventsUseCaseImpl getAllEventsUseCase;

    @Test
    void getAllEvents_ShouldReturnAllEvents() {
        // Arrange
        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(1L);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(2L);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(1L);

        EventEntity eventEntity1 = new EventEntity();
        eventEntity1.setId(1L);
        eventEntity1.setBoxer1(boxer1);
        eventEntity1.setBoxer2(boxer2);
        eventEntity1.setOrganizer(organizer);
        eventEntity1.setDate(LocalDateTime.now());
        eventEntity1.setStatus(EventStatus.SCHEDULED);
        eventEntity1.setPlace("Place 1");

        EventEntity eventEntity2 = new EventEntity();
        eventEntity2.setId(2L);
        eventEntity2.setBoxer1(boxer1);
        eventEntity2.setBoxer2(boxer2);
        eventEntity2.setOrganizer(organizer);
        eventEntity2.setDate(LocalDateTime.now().plusDays(1));
        eventEntity2.setStatus(EventStatus.SCHEDULED);
        eventEntity2.setPlace("Place 2");

        when(eventRepository.findAll()).thenReturn(Arrays.asList(eventEntity1, eventEntity2));

        // Act
        List<GetEventResponse> responses = getAllEventsUseCase.getAllEvents();

        // Assert
        assertEquals(2, responses.size());
        assertEquals(1L, responses.get(0).getEvent().getId());
        assertEquals(2L, responses.get(1).getEvent().getId());
        assertEquals("Place 1", responses.get(0).getEvent().getPlace());
        assertEquals("Place 2", responses.get(1).getEvent().getPlace());

        verify(eventRepository, times(1)).findAll();
    }
}
