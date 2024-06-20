package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetEventsCountByOrganizerResponse;
import com.knockoutticket.backend.persistence.EventRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetEventsCountByOrganizerUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private GetEventsCountByOrganizerUseCaseImpl getEventsCountByOrganizerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCountEventsByOrganizer() {
        // Arrange
        AppUserEntity organizer1 = new AppUserEntity();
        organizer1.setId(1L);
        organizer1.setUsername("Organizer1");

        AppUserEntity organizer2 = new AppUserEntity();
        organizer2.setId(2L);
        organizer2.setUsername("Organizer2");

        GetEventsCountByOrganizerResponse response1 = new GetEventsCountByOrganizerResponse(organizer1, 5L);
        GetEventsCountByOrganizerResponse response2 = new GetEventsCountByOrganizerResponse(organizer2, 3L);
        List<GetEventsCountByOrganizerResponse> mockResponses = Arrays.asList(response1, response2);

        when(eventRepository.countEventsByOrganizer()).thenReturn(mockResponses);

        // Act
        List<GetEventsCountByOrganizerResponse> responses = getEventsCountByOrganizerUseCase.countEventsByOrganizer();

        // Assert
        assertEquals(2, responses.size());
        assertEquals(organizer1, responses.get(0).getOrganizer());
        assertEquals(5L, responses.get(0).getEventCount());
        assertEquals(organizer2, responses.get(1).getOrganizer());
        assertEquals(3L, responses.get(1).getEventCount());
    }
}
