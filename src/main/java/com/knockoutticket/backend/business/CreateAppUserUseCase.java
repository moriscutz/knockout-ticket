package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateAppUserRequest;
import com.knockoutticket.backend.domain.responses.CreateAppUserResponse;

public interface CreateAppUserUseCase {
    CreateAppUserResponse createAppUser(CreateAppUserRequest request);
}