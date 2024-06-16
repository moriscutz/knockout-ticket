package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEventToFightNightRequest {
    @NotNull
    private Long eventFightNightId;
    @NotNull
    private Long boxerId1;
    @NotNull
    private Long boxerId2;
    @NotNull
    private Long organizerId;
    @NotNull
    private LocalDateTime eventDate;
    @NotBlank
    private String status;
    @NotBlank
    private String place;
}