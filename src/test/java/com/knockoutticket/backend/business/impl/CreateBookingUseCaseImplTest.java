package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateBookingResponse;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateBookingUseCaseImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EventFightNightRepository eventFightNightRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private CreateBookingUseCaseImpl createBookingUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_ShouldReturnResponse_WhenBookingIsSuccessful() {
        // Arrange
        Long eventFightNightId = 1L;
        Long customerId = 2L;
        CreateBookingRequest request = CreateBookingRequest.builder()
                .eventFightNightId(eventFightNightId)
                .customerId(customerId)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        EventFightNightEntity eventFightNight = EventFightNightEntity.builder()
                .id(eventFightNightId)
                .build();

        AppUserEntity appUser = AppUserEntity.builder()
                .id(customerId)
                .build();

        BookingEntity bookingEntity = BookingEntity.builder()
                .id(3L)
                .customerId(appUser)
                .eventFightNight(eventFightNight)
                .build();

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(eventFightNight));
        when(appUserRepository.findById(customerId)).thenReturn(Optional.of(appUser));
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);

        // Act
        CreateBookingResponse response = createBookingUseCase.createBooking(request);

        // Assert
        assertNotNull(response);
        assertEquals(bookingEntity.getId(), response.getId());
        verify(eventFightNightRepository, times(1)).findById(eventFightNightId);
        verify(appUserRepository, times(1)).findById(customerId);
        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }

    @Test
    void createBooking_ShouldThrowEventFightNightNotFoundException_WhenEventFightNightDoesNotExist() {
        // Arrange
        Long eventFightNightId = 1L;
        Long customerId = 2L;
        CreateBookingRequest request = CreateBookingRequest.builder()
                .eventFightNightId(eventFightNightId)
                .customerId(customerId)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EventFightNightNotFoundException.class, () -> {
            createBookingUseCase.createBooking(request);
        });

        verify(eventFightNightRepository, times(1)).findById(eventFightNightId);
        verify(appUserRepository, never()).findById(anyLong());
        verify(bookingRepository, never()).save(any(BookingEntity.class));
    }

    @Test
    void createBooking_ShouldThrowUserNotFoundException_WhenUserDoesNotExist() {
        // Arrange
        Long eventFightNightId = 1L;
        Long customerId = 2L;
        CreateBookingRequest request = CreateBookingRequest.builder()
                .eventFightNightId(eventFightNightId)
                .customerId(customerId)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        EventFightNightEntity eventFightNight = EventFightNightEntity.builder()
                .id(eventFightNightId)
                .build();

        when(eventFightNightRepository.findById(eventFightNightId)).thenReturn(Optional.of(eventFightNight));
        when(appUserRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            createBookingUseCase.createBooking(request);
        });

        verify(eventFightNightRepository, times(1)).findById(eventFightNightId);
        verify(appUserRepository, times(1)).findById(customerId);
        verify(bookingRepository, never()).save(any(BookingEntity.class));
    }
}
