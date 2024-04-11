package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;

public interface CreateBoxerUseCase {
    CreateBoxerResponse createBoxer (CreateBoxerRequest request);
}
