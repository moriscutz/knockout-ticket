package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.LoginRequest;

public interface LoginUseCase {
    boolean login(LoginRequest loginRequest);
}
