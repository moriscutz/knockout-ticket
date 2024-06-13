package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateBookingUseCase;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateBookingResponse;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.entity.BookingEntity;
import com.knockoutticket.backend.persistence.entity.EventFightNightEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final EventFightNightRepository eventFightNightRepository;

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        EventFightNightEntity eventFightNight = eventFightNightRepository.findById(request.getEventFightNightId())
                .orElseThrow(() -> new EventFightNightNotFoundException(request.getEventFightNightId()));

        BookingEntity booking = BookingEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .eventFightNight(eventFightNight)
                .build();

        BookingEntity savedBooking = bookingRepository.save(booking);

        return new CreateBookingResponse(savedBooking.getId());
    }
}