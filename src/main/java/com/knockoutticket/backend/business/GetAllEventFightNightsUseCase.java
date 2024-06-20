package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetEventFightNightResponse;

import java.util.List;

public interface GetAllEventFightNightsUseCase {
    List<GetEventFightNightResponse> getAllEventFightNights();
}
