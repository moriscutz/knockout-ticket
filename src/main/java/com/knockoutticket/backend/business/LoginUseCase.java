package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.LoginRequest;
import com.knockoutticket.backend.domain.responses.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
