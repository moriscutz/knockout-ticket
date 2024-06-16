package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Booking;
import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import com.knockoutticket.backend.persistence.entity.BookingEntity;

import java.util.List;
public class BookingConverter {

    public static Booking toBooking(BookingEntity bookingEntity) {
        return Booking.builder()
                .id(bookingEntity.getId())
                .name(bookingEntity.getName())
                .email(bookingEntity.getEmail())
                .eventFightNightId(bookingEntity.getEventFightNight().getId())
                .build();
    }

    public static GetBookingsForUserResponse toGetBookingsForUserResponse(List<BookingEntity> bookings) {
        List<Booking> bookingDTOs = bookings.stream()
                .map(BookingConverter::toBooking)
                .toList();

        return new GetBookingsForUserResponse(bookingDTOs);
    }
}
