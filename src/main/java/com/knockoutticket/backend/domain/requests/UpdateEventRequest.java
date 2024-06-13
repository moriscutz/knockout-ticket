package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.EventStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEventRequest {
    @NotNull
    private EventStatus status;
}
