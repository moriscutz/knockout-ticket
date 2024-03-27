package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetAppUserResponse;

import java.util.List;

public interface GetAllAppUsersUseCase {
    List<GetAppUserResponse> getAllAppUsers();
}
