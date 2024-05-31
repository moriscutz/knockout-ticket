package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.GetEventBoxersRequest;
import com.knockoutticket.backend.domain.responses.GetEventBoxersResponse;

public interface GetEventBoxersUseCase {
    GetEventBoxersResponse getEventBoxers(GetEventBoxersRequest request);
}
