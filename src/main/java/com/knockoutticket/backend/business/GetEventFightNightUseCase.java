package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;

public interface GetEventFightNightUseCase {
    GetEventFightNightResponse getEventFightNight(Long id);
}
