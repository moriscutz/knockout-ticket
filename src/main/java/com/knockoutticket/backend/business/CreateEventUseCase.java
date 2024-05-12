package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateEventRequest;
import com.knockoutticket.backend.domain.responses.CreateEventResponse;

public interface CreateEventUseCase {

    CreateEventResponse createEvent(CreateEventRequest request);
}
