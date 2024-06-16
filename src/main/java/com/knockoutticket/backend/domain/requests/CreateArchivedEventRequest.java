package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateArchivedEventRequest {
    @NotNull
    private Long organizerId;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long winnerId;

    @NotNull
    private Long loserId;

    @NotBlank
    private String place;

    @NotNull
    private Long eventId;
}
