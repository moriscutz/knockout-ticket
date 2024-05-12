package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {
    @NotNull
    private Long boxerId1;
    @NotNull
    private Long boxerId2;
    @NotNull
    private Long organizerId;
    @NotNull
    private LocalDateTime date;
    @NotBlank
    private String place;
}
