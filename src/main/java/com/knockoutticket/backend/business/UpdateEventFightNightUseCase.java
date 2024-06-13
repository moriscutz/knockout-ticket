package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.UpdateEventFightNightResponse;

public interface UpdateEventFightNightUseCase {
    UpdateEventFightNightResponse updateEventFightNight(Long id, UpdateEventFightNightRequest request);
}
