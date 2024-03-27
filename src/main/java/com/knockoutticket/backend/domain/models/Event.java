package com.knockoutticket.backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Long id;
    private Boxer boxer1;
    private Boxer boxer2;
    private EventOrganizer organizer;
    private LocalDateTime date;
    private EventStatus status;
    private Boxer winner;
    private String place;
}
