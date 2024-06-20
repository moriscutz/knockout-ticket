package com.knockoutticket.backend.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ArchivedEvent {
    private Long id;
    private Long organizerId;
    private LocalDateTime date;
    private Long winnerId;
    private Long losserId;
    private String place;
}
