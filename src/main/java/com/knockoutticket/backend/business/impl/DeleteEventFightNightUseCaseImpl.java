package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventFightNightUseCase;
import com.knockoutticket.backend.business.exception.EventFightNightNotFoundException;
import com.knockoutticket.backend.persistence.BookingRepository;
import com.knockoutticket.backend.persistence.EventFightNightRepository;
import com.knockoutticket.backend.persistence.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEventFightNightUseCaseImpl implements DeleteEventFightNightUseCase {

    private final EventFightNightRepository eventFightNightRepository;
    private final BookingRepository bookingRepository;
    @Transactional
    @Override
    public void deleteEventFightNight(Long id) {
        if (!eventFightNightRepository.existsById(id)) {
           throw new EventFightNightNotFoundException(id);
        }
        else {
            eventFightNightRepository.deleteAllEventsByFightNightId(id);
            bookingRepository.deleteAllBookingsByFightNightId(id);
            eventFightNightRepository.deleteById(id);
        }
    }
}
