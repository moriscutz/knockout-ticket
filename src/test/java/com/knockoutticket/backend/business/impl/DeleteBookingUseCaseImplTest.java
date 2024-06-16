package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.persistence.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteBookingUseCaseImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private DeleteBookingUseCaseImpl deleteBookingUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteBooking() {
        // Arrange
        Long bookingId = 1L;

        // Act
        deleteBookingUseCase.deleteBooking(bookingId);

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}
