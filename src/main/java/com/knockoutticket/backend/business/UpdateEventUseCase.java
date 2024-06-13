package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateEventRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventResponse;

public interface UpdateEventUseCase {
    UpdateEventResponse updateEvent(Long id, UpdateEventRequest request);
}
