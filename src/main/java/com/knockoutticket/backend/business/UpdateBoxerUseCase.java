package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateBoxerRequest;

public interface UpdateBoxerUseCase {
    void updateBoxer(Long username, UpdateBoxerRequest request);
}
