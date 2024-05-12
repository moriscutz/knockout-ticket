package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetBoxerResponse;

import java.util.List;

public interface GetAllBoxersUseCase {
    List<GetBoxerResponse> getAllBoxers();
}
