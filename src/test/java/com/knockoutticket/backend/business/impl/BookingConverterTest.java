package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Booking;
import com.knockoutticket.backend.domain.models.EventStatus;
import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import com.knockoutticket.backend.persistence.entity.EventEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingConverterTest {

    @Mock
    private EventFightNightEntity eventFightNightEntityMock;

    @Mock
    private EventEntity eventEntityMock;

    @Mock
    private BoxerEntity boxer1Mock;

    @Mock
    private BoxerEntity boxer2Mock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToBooking() {
        when(eventFightNightEntityMock.getId()).thenReturn(1L);
        when(eventFightNightEntityMock.getTitle()).thenReturn("Fight Night");
        when(eventFightNightEntityMock.getDate()).thenReturn(LocalDate.now());
        when(eventFightNightEntityMock.getStartTime()).thenReturn(LocalTime.now());
        when(eventFightNightEntityMock.getEndTime()).thenReturn(LocalTime.now().plusHours(2));
        when(eventFightNightEntityMock.getPlace()).thenReturn("Stadium");
        when(eventFightNightEntityMock.getEvents()).thenReturn(Arrays.asList(eventEntityMock));

        when(eventEntityMock.getId()).thenReturn(1L);
        when(eventEntityMock.getBoxer1()).thenReturn(boxer1Mock);
        when(eventEntityMock.getBoxer2()).thenReturn(boxer2Mock);
        when(eventEntityMock.getDate()).thenReturn(LocalDate.now().atTime(LocalTime.now()));
        when(eventEntityMock.getPlace()).thenReturn("Stadium");
        when(eventEntityMock.getStatus()).thenReturn(EventStatus.SCHEDULED);

        when(boxer1Mock.getId()).thenReturn(1L);
        when(boxer2Mock.getId()).thenReturn(2L);

        BookingEntity bookingEntity = BookingEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .eventFightNight(eventFightNightEntityMock)
                .build();

        Booking booking = BookingConverter.toBooking(bookingEntity);

        assertEquals(1L, booking.getId());
        assertEquals("John Doe", booking.getName());
        assertEquals("john.doe@example.com", booking.getEmail());
        assertEquals(1L, booking.getEventFightNight().getId());
        assertEquals("Fight Night", booking.getEventFightNight().getTitle());

        verify(eventFightNightEntityMock, times(1)).getId();
        verify(eventFightNightEntityMock, times(1)).getTitle();
    }

    @Test
    void testToGetBookingsForUserResponse() {
        when(eventFightNightEntityMock.getId()).thenReturn(1L);
        when(eventFightNightEntityMock.getTitle()).thenReturn("Fight Night");
        when(eventFightNightEntityMock.getDate()).thenReturn(LocalDate.now());
        when(eventFightNightEntityMock.getStartTime()).thenReturn(LocalTime.now());
        when(eventFightNightEntityMock.getEndTime()).thenReturn(LocalTime.now().plusHours(2));
        when(eventFightNightEntityMock.getPlace()).thenReturn("Stadium");
        when(eventFightNightEntityMock.getEvents()).thenReturn(Arrays.asList(eventEntityMock));

        when(eventEntityMock.getId()).thenReturn(1L);
        when(eventEntityMock.getBoxer1()).thenReturn(boxer1Mock);
        when(eventEntityMock.getBoxer2()).thenReturn(boxer2Mock);
        when(eventEntityMock.getDate()).thenReturn(LocalDate.now().atTime(LocalTime.now()));
        when(eventEntityMock.getPlace()).thenReturn("Stadium");
        when(eventEntityMock.getStatus()).thenReturn(EventStatus.SCHEDULED);

        when(boxer1Mock.getId()).thenReturn(1L);
        when(boxer2Mock.getId()).thenReturn(2L);

        BookingEntity bookingEntity1 = BookingEntity.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .eventFightNight(eventFightNightEntityMock)
                .build();

        BookingEntity bookingEntity2 = BookingEntity.builder()
                .id(2L)
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .eventFightNight(eventFightNightEntityMock)
                .build();

        List<BookingEntity> bookingEntities = Arrays.asList(bookingEntity1, bookingEntity2);
        GetBookingsForUserResponse response = BookingConverter.toGetBookingsForUserResponse(bookingEntities);

        assertEquals(2, response.getBookings().size());

        Booking booking1 = response.getBookings().get(0);
        assertEquals(1L, booking1.getId());
        assertEquals("John Doe", booking1.getName());
        assertEquals("john.doe@example.com", booking1.getEmail());
        assertEquals(1L, booking1.getEventFightNight().getId());
        assertEquals("Fight Night", booking1.getEventFightNight().getTitle());

        Booking booking2 = response.getBookings().get(1);
        assertEquals(2L, booking2.getId());
        assertEquals("Jane Doe", booking2.getName());
        assertEquals("jane.doe@example.com", booking2.getEmail());
        assertEquals(1L, booking2.getEventFightNight().getId());
        assertEquals("Fight Night", booking2.getEventFightNight().getTitle());

        verify(eventFightNightEntityMock, times(2)).getId();
        verify(eventFightNightEntityMock, times(2)).getTitle();
    }
}
