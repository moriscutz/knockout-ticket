package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetEventResponse {
    private Event event;
}
