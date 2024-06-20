package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Booking;
import com.knockoutticket.backend.domain.models.Event;
import com.knockoutticket.backend.domain.models.EventFightNight;
import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import com.knockoutticket.backend.persistence.entity.BookingEntity;

import java.util.List;
public class BookingConverter {
    private BookingConverter() {
        throw new IllegalStateException("This is an utility class, and cannot be instantiated.");
    }
    public static Booking toBooking(BookingEntity bookingEntity) {
        EventFightNight eventFightNight = EventFightNight.builder()
                .id(bookingEntity.getEventFightNight().getId())
                .title(bookingEntity.getEventFightNight().getTitle())
                .date(bookingEntity.getEventFightNight().getDate())
                .startTime(bookingEntity.getEventFightNight().getStartTime())
                .endTime(bookingEntity.getEventFightNight().getEndTime())
                .place(bookingEntity.getEventFightNight().getPlace())
                .events(bookingEntity.getEventFightNight().getEvents().stream()
                        .map(event -> Event.builder()
                                .id(event.getId())
                                .boxerId1(event.getBoxer1().getId())
                                .boxerId2(event.getBoxer2().getId())
                                .date(event.getDate())
                                .place(event.getPlace())
                                .status(event.getStatus())
                                .build())
                        .toList())
                .build();

        return Booking.builder()
                .id(bookingEntity.getId())
                .name(bookingEntity.getName())
                .email(bookingEntity.getEmail())
                .eventFightNight(eventFightNight)
                .build();
    }

    public static GetBookingsForUserResponse toGetBookingsForUserResponse(List<BookingEntity> bookings) {
        List<Booking> bookingDTOs = bookings.stream()
                .map(BookingConverter::toBooking)
                .toList();

        return new GetBookingsForUserResponse(bookingDTOs);
    }
}
