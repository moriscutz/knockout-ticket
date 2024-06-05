package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetEventResponse;

public interface GetEventUseCase {

    GetEventResponse getEvent(Long id);
}
