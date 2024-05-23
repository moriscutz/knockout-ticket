package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetEventResponse;

import java.util.List;

public interface GetAllEventsUseCase {

    List<GetEventResponse> getAllEvents();
}
