package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteBookingUseCase;
import com.knockoutticket.backend.persistence.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookingUseCaseImpl implements DeleteBookingUseCase {

    private final BookingRepository bookingRepository;

    @Transactional
    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}