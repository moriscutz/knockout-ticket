package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetBoxerResponse;

public interface GetBoxerByIdUseCase {
    GetBoxerResponse getBoxerById(Long id);
}
