package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;


public interface GetBookingsForUserUseCase {
    GetBookingsForUserResponse getBookingsForUser(Long userId);
}
