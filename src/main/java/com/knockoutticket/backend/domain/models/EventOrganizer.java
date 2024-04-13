package com.knockoutticket.backend.domain.models;

import lombok.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventOrganizer {
    private Long id;
    private String organizationName;
    private Map<Long, Event> eventMap;

    public EventOrganizer(String organizationName) {
        this.organizationName = organizationName;
        this.eventMap = new HashMap<>();
    }
}
