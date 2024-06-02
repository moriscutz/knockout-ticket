package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EventConverterTest {

    @Test
    void toEventDTO_whenEventEntityProvided_shouldReturnEvent() {
        // Arrange
        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(1L);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(2L);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(3L);

        BoxerEntity winner = new BoxerEntity();
        winner.setId(4L);

        EventEntity eventEntity = EventEntity.builder()
                .id(5L)
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(LocalDateTime.of(2023, 6, 1, 12, 0))
                .status(EventStatus.SCHEDULED)
                .winner(winner)
                .place("Some Place")
                .build();

        // Act
        Event event = EventConverter.toEventDTO(eventEntity);

        // Assert
        assertEquals(5L, event.getId());
        assertEquals(1L, event.getBoxerId1());
        assertEquals(2L, event.getBoxerId2());
        assertEquals(3L, event.getOrganizerId());
        assertEquals(LocalDateTime.of(2023, 6, 1, 12, 0), event.getDate());
        assertEquals(EventStatus.SCHEDULED, event.getStatus());
        assertEquals(4L, event.getWinnerId());
        assertEquals("Some Place", event.getPlace());
    }

    @Test
    void toEventDTO_whenEventEntityWithoutWinnerProvided_shouldReturnEventWithNullWinnerId() {
        // Arrange
        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(1L);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(2L);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(3L);

        EventEntity eventEntity = EventEntity.builder()
                .id(5L)
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(LocalDateTime.of(2023, 6, 1, 12, 0))
                .status(EventStatus.SCHEDULED)
                .winner(null)
                .place("Some Place")
                .build();

        // Act
        Event event = EventConverter.toEventDTO(eventEntity);

        // Assert
        assertEquals(5L, event.getId());
        assertEquals(1L, event.getBoxerId1());
        assertEquals(2L, event.getBoxerId2());
        assertEquals(3L, event.getOrganizerId());
        assertEquals(LocalDateTime.of(2023, 6, 1, 12, 0), event.getDate());
        assertEquals(EventStatus.SCHEDULED, event.getStatus());
        assertNull(event.getWinnerId());
        assertEquals("Some Place", event.getPlace());
    }

    @Test
    void toEventEntity_whenEventProvided_shouldReturnEventEntity() {
        // Arrange
        Event event = Event.builder()
                .id(5L)
                .boxerId1(1L)
                .boxerId2(2L)
                .organizerId(3L)
                .date(LocalDateTime.of(2023, 6, 1, 12, 0))
                .status(EventStatus.SCHEDULED)
                .winnerId(4L)
                .place("Some Place")
                .build();

        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(1L);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(2L);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(3L);

        BoxerEntity winner = new BoxerEntity();
        winner.setId(4L);

        // Act
        EventEntity eventEntity = EventConverter.toEventEntity(event, boxer1, boxer2, organizer, winner);

        // Assert
        assertEquals(5L, eventEntity.getId());
        assertEquals(boxer1, eventEntity.getBoxer1());
        assertEquals(boxer2, eventEntity.getBoxer2());
        assertEquals(organizer, eventEntity.getOrganizer());
        assertEquals(LocalDateTime.of(2023, 6, 1, 12, 0), eventEntity.getDate());
        assertEquals(EventStatus.SCHEDULED, eventEntity.getStatus());
        assertEquals(winner, eventEntity.getWinner());
        assertEquals("Some Place", eventEntity.getPlace());
    }

    @Test
    void toEventEntity_whenEventWithoutWinnerProvided_shouldReturnEventEntityWithNullWinner() {
        // Arrange
        Event event = Event.builder()
                .id(5L)
                .boxerId1(1L)
                .boxerId2(2L)
                .organizerId(3L)
                .date(LocalDateTime.of(2023, 6, 1, 12, 0))
                .status(EventStatus.SCHEDULED)
                .winnerId(null)
                .place("Some Place")
                .build();

        BoxerEntity boxer1 = new BoxerEntity();
        boxer1.setId(1L);

        BoxerEntity boxer2 = new BoxerEntity();
        boxer2.setId(2L);

        AppUserEntity organizer = new AppUserEntity();
        organizer.setId(3L);

        // Act
        EventEntity eventEntity = EventConverter.toEventEntity(event, boxer1, boxer2, organizer, null);

        // Assert
        assertEquals(5L, eventEntity.getId());
        assertEquals(boxer1, eventEntity.getBoxer1());
        assertEquals(boxer2, eventEntity.getBoxer2());
        assertEquals(organizer, eventEntity.getOrganizer());
        assertEquals(LocalDateTime.of(2023, 6, 1, 12, 0), eventEntity.getDate());
        assertEquals(EventStatus.SCHEDULED, eventEntity.getStatus());
        assertNull(eventEntity.getWinner());
        assertEquals("Some Place", eventEntity.getPlace());
    }
}
