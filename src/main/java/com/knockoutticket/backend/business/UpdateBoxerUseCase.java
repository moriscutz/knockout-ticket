package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateBoxerRequest;
import com.knockoutticket.backend.domain.responses.UpdateBoxerResponse;

public interface UpdateBoxerUseCase {
    UpdateBoxerResponse updateBoxer (UpdateBoxerRequest request);
}
