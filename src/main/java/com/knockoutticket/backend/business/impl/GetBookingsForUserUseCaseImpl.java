package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.GetBookingsForUserUseCase;
import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBookingsForUserUseCaseImpl implements GetBookingsForUserUseCase {

    private final BookingRepository bookingRepository;

    @Override
    public GetBookingsForUserResponse getBookingsForUser(Long userId) {
        List<BookingEntity> bookings = bookingRepository.findBookingsByCustomerId(userId);
        return BookingConverter.toGetBookingsForUserResponse(bookings);
    }
}
