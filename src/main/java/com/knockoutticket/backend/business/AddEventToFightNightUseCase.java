package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.AddEventToFightNightRequest;
import com.knockoutticket.backend.domain.responses.AddEventToFightNightResponse;

public interface AddEventToFightNightUseCase {
    AddEventToFightNightResponse addEventToFightNight(AddEventToFightNightRequest request);
}
