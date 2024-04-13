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
    private Long boxerId1;
    private Long boxerId2;
    private Long organizerId;
    private LocalDateTime date;
    private EventStatus status;
    private Long winnerId;
    private String place;
}
