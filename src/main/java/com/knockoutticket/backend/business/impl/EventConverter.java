package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;

public class EventConverter {

    private EventConverter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Event toEventDTO(EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .boxerId1(eventEntity.getBoxer1() != null ? eventEntity.getBoxer1().getId() : null)
                .boxerId2(eventEntity.getBoxer2() != null ? eventEntity.getBoxer2().getId() : null)
                .organizerId(eventEntity.getOrganizer().getId())
                .date(eventEntity.getDate())
                .status(eventEntity.getStatus())
                .winnerId(eventEntity.getWinner() != null ? eventEntity.getWinner().getId() : null)
                .place(eventEntity.getPlace())
                .build();
    }

    public static EventEntity toEventEntity(Event event, BoxerEntity boxer1, BoxerEntity boxer2, AppUserEntity organizer, BoxerEntity winner) {
        return EventEntity.builder()
                .id(event.getId())
                .boxer1(boxer1)
                .boxer2(boxer2)
                .organizer(organizer)
                .date(event.getDate())
                .status(event.getStatus())
                .winner(winner)
                .place(event.getPlace())
                .build();
    }
}
