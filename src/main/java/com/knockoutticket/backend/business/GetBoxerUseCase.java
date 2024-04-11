package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetBoxerResponse;

import java.util.List;

public interface GetBoxerUseCase {
    GetBoxerResponse getBoxer(Long username);
    List<GetBoxerResponse> getAllBoxers();
}
