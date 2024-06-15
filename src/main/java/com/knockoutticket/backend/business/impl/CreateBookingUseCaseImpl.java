package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.CreateBookingUseCase;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final EventFightNightRepository eventFightNightRepository;
    private final AppUserRepository appUserRepository;


    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        EventFightNightEntity eventFightNight = eventFightNightRepository.findById(request.getEventFightNightId())
                .orElseThrow(() -> new EventFightNightNotFoundException(request.getEventFightNightId()));

        Optional<AppUserEntity> appUser = appUserRepository.findById(request.getCustomer_id());



        if(appUser.isEmpty())
        {
         throw new UserNotFoundException();
        }

        AppUserEntity appUserEntity = appUser.get();

        BookingEntity booking = BookingEntity.builder()
                .customer_id(appUserEntity)
                .name(request.getName())
                .email(request.getEmail())
                .eventFightNight(eventFightNight)
                .build();

        BookingEntity savedBooking = bookingRepository.save(booking);

        return new CreateBookingResponse(savedBooking.getId());
    }
}