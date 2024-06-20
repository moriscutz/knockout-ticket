package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse;

import java.util.List;

public interface GetBookingsCountByCustomerUseCase {
    List<GetBookingsCountByCustomerResponse> getCountBookingsByCustomer();
}
