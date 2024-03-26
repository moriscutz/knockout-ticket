package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetAppUserResponse;

public interface GetAppUserUseCase {
    GetAppUserResponse getAppUser(Long username);
}
