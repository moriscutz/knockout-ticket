package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.EventFightNight;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventFightNightConverterTest {

    @Test
    void testToEventFightNightDTO() {
        // Arrange
        EventFightNightEntity entity = new EventFightNightEntity();
        entity.setId(1L);
        entity.setTitle("Fight Night");
        entity.setDate(LocalDate.of(2024, 6, 15));
        entity.setStartTime(LocalTime.of(18, 0));
        entity.setEndTime(LocalTime.of(21, 0));
        entity.setPlace("Madison Square Garden");

        // Act
        EventFightNight dto = EventFightNightConverter.toEventFightNightDTO(entity);

        // Assert
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTitle(), dto.getTitle());
        assertEquals(entity.getDate(), dto.getDate());
        assertEquals(entity.getStartTime(), dto.getStartTime());
        assertEquals(entity.getEndTime(), dto.getEndTime());
        assertEquals(entity.getPlace(), dto.getPlace());
    }

    @Test
    void testToEventFightNightEntity() {
        // Arrange
        EventFightNight dto = EventFightNight.builder()
                .id(1L)
                .title("Fight Night")
                .date(LocalDate.of(2024, 6, 15))
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(21, 0))
                .place("Madison Square Garden")
                .build();

        // Act
        EventFightNightEntity entity = EventFightNightConverter.toEventFightNightEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTitle(), entity.getTitle());
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(dto.getStartTime(), entity.getStartTime());
        assertEquals(dto.getEndTime(), entity.getEndTime());
        assertEquals(dto.getPlace(), entity.getPlace());
    }
}
