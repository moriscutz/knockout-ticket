package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateEventFightNightRequest;
import com.knockoutticket.backend.domain.responses.CreateEventFightNightResponse;

public interface CreateEventFightNightUseCase {
   CreateEventFightNightResponse createEventFightNight(CreateEventFightNightRequest request);
}
