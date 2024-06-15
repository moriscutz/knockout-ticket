package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateArchivedEventRequest;
import com.knockoutticket.backend.domain.responses.CreateArchivedEventResponse;

public interface CreateArchivedEventUseCase {
    CreateArchivedEventResponse createArchivedEvent(CreateArchivedEventRequest request);
}
