package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateAppUserRequest;

public interface UpdateAppUserUseCase {
    void updateAppUser(Long username, UpdateAppUserRequest request);
}