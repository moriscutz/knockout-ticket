package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateBookingResponse;

public interface CreateBookingUseCase {
    CreateBookingResponse createBooking(CreateBookingRequest request);
}
