package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteBookingsForUserUseCase;
import com.knockoutticket.backend.persistence.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBookingsForUserUseCaseImpl implements DeleteBookingsForUserUseCase {
    private final BookingRepository bookingRepository;

    @Transactional
    @Override
    public void deleteBookingsForUser(Long userId) {
        bookingRepository.deleteAllBookingsByCustomerId(userId);
    }
}
