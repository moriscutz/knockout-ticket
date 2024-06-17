//package com.knockoutticket.backend.business.impl;
//
//import com.knockoutticket.backend.domain.models.Booking;
//import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
//import com.knockoutticket.backend.persistence.entity.BookingEntity;
//import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class BookingConverterTest {
//
//    @Test
//    void testToBooking() {
//        EventFightNightEntity eventFightNight = new EventFightNightEntity();
//        eventFightNight.setId(1L);
//
//        BookingEntity bookingEntity = BookingEntity.builder()
//                .id(1L)
//                .name("John Doe")
//                .email("john.doe@example.com")
//                .eventFightNight(eventFightNight)
//                .build();
//
//        Booking booking = BookingConverter.toBooking(bookingEntity);
//
//        assertEquals(1L, booking.getId());
//        assertEquals("John Doe", booking.getName());
//        assertEquals("john.doe@example.com", booking.getEmail());
//        assertEquals(1L, booking.getEventFightNightId());
//    }
//
//    @Test
//    void testToGetBookingsForUserResponse() {
//        EventFightNightEntity eventFightNight = new EventFightNightEntity();
//        eventFightNight.setId(1L);
//
//        BookingEntity bookingEntity1 = BookingEntity.builder()
//                .id(1L)
//                .name("John Doe")
//                .email("john.doe@example.com")
//                .eventFightNight(eventFightNight)
//                .build();
//
//        BookingEntity bookingEntity2 = BookingEntity.builder()
//                .id(2L)
//                .name("Jane Doe")
//                .email("jane.doe@example.com")
//                .eventFightNight(eventFightNight)
//                .build();
//
//        List<BookingEntity> bookingEntities = Arrays.asList(bookingEntity1, bookingEntity2);
//        GetBookingsForUserResponse response = BookingConverter.toGetBookingsForUserResponse(bookingEntities);
//
//        assertEquals(2, response.getBookings().size());
//
//        Booking booking1 = response.getBookings().get(0);
//        assertEquals(1L, booking1.getId());
//        assertEquals("John Doe", booking1.getName());
//        assertEquals("john.doe@example.com", booking1.getEmail());
//        assertEquals(1L, booking1.getEventFightNightId());
//
//        Booking booking2 = response.getBookings().get(1);
//        assertEquals(2L, booking2.getId());
//        assertEquals("Jane Doe", booking2.getName());
//        assertEquals("jane.doe@example.com", booking2.getEmail());
//        assertEquals(1L, booking2.getEventFightNightId());
//    }
//}
