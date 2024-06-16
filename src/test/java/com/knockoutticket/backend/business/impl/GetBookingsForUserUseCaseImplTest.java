package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetBookingsForUserUseCaseImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private GetBookingsForUserUseCaseImpl getBookingsForUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnBookingsForUser() {
        // Arrange
        Long userId = 1L;
        EventFightNightEntity eventFightNight = new EventFightNightEntity();
        eventFightNight.setId(1L);

        List<BookingEntity> mockBookings = new ArrayList<>();
        mockBookings.add(BookingEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .eventFightNight(eventFightNight)
                .build());
        mockBookings.add(BookingEntity.builder()
                .id(2L)
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .eventFightNight(eventFightNight)
                .build());

        when(bookingRepository.findBookingsByCustomerId(userId)).thenReturn(mockBookings);

        // Act
        GetBookingsForUserResponse response = getBookingsForUserUseCase.getBookingsForUser(userId);

        // Assert
        assertEquals(2, response.getBookings().size());
        verify(bookingRepository, times(1)).findBookingsByCustomerId(userId);
    }

    @Test
    void shouldReturnEmptyBookingsForUser() {
        // Arrange
        Long userId = 1L;
        List<BookingEntity> mockBookings = new ArrayList<>();

        when(bookingRepository.findBookingsByCustomerId(userId)).thenReturn(mockBookings);

        // Act
        GetBookingsForUserResponse response = getBookingsForUserUseCase.getBookingsForUser(userId);

        // Assert
        assertEquals(0, response.getBookings().size());
        verify(bookingRepository, times(1)).findBookingsByCustomerId(userId);
    }
}
