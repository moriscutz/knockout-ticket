package com.knockoutticket.backend.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventOrganizer extends AppUser {
    private String organizationName;
    private Map<Long, Event> eventMap;

    public EventOrganizer(String organizationName) {
        this.organizationName = organizationName;
        this.eventMap = new HashMap<>();
    }
}
