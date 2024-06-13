package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.EventFightNight;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;

public class EventFightNightConverter {

    private EventFightNightConverter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static EventFightNight toEventFightNightDTO(EventFightNightEntity entity) {
        return EventFightNight.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .date(entity.getDate())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .place(entity.getPlace())
                .build();
    }

    public static EventFightNightEntity toEventFightNightEntity(EventFightNight dto) {
        EventFightNightEntity entity = new EventFightNightEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setPlace(dto.getPlace());
        return entity;
    }
}
