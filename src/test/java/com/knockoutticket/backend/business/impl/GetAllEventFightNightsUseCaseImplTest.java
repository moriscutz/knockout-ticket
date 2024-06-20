package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GetAllEventFightNightsUseCaseImplTest {

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @InjectMocks
    private GetAllEventFightNightsUseCaseImpl getAllEventFightNightsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventFightNights_ShouldReturnAllEventFightNights() {
        // Arrange
        EventFightNightEntity event1 = EventFightNightEntity.builder()
                .id(1L)
                .title("Fight Night 1")
                .date(LocalDate.of(2024, 6, 15))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place("Madison Square Garden")
                .build();

        EventFightNightEntity event2 = EventFightNightEntity.builder()
                .id(2L)
                .title("Fight Night 2")
                .date(LocalDate.of(2024, 7, 20))
                .startTime(LocalTime.of(19, 0))
                .endTime(LocalTime.of(22, 0))
                .place("Staples Center")
                .build();

        when(eventFightNightRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        // Act
        List<GetEventFightNightResponse> responses = getAllEventFightNightsUseCase.getAllEventFightNights();

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());

        GetEventFightNightResponse response1 = responses.get(0);
        assertEquals(1L, response1.getId());
        assertEquals("Fight Night 1", response1.getTitle());
        assertEquals(LocalDate.of(2024, 6, 15), response1.getDate());
        assertEquals(LocalTime.of(18, 0), response1.getStartTime());
        assertEquals(LocalTime.of(21, 0), response1.getEndTime());
        assertEquals("Madison Square Garden", response1.getPlace());

        GetEventFightNightResponse response2 = responses.get(1);
        assertEquals(2L, response2.getId());
        assertEquals("Fight Night 2", response2.getTitle());
        assertEquals(LocalDate.of(2024, 7, 20), response2.getDate());
        assertEquals(LocalTime.of(19, 0), response2.getStartTime());
        assertEquals(LocalTime.of(22, 0), response2.getEndTime());
        assertEquals("Staples Center", response2.getPlace());
    }
}
