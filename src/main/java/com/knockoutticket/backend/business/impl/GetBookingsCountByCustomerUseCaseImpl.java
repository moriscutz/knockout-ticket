package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetBookingsCountByCustomerUseCase;
import com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse;
import com.knockoutticket.backend.persistence.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBookingsCountByCustomerUseCaseImpl implements GetBookingsCountByCustomerUseCase {

    private final BookingRepository bookingRepository;

    @Override
    public List<GetBookingsCountByCustomerResponse> getCountBookingsByCustomer() {
        return bookingRepository.countBookingsByCustomer();
    }
}
