package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventResponse {
    private Long Id;
    private EventStatus status;
}
