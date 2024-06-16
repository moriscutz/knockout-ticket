package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.business.CreateBookingUseCase;
import com.knockoutticket.backend.business.DeleteBookingUseCase;
import com.knockoutticket.backend.business.GetBookingsCountByCustomerUseCase;
import com.knockoutticket.backend.business.GetBookingsForUserUseCase;
import com.knockoutticket.backend.domain.requests.CreateBookingRequest;
import com.knockoutticket.backend.domain.responses.CreateBookingResponse;
import com.knockoutticket.backend.domain.responses.GetBookingsCountByCustomerResponse;
import com.knockoutticket.backend.domain.responses.GetBookingsForUserResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;
    private final GetBookingsCountByCustomerUseCase getBookingsCountByCustomerUseCase;
    private final GetBookingsForUserUseCase getBookingsForUserUseCase;
    private final DeleteBookingUseCase deleteBookingUseCase;

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = createBookingUseCase.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RolesAllowed({"EVENT_ORGANIZER"})
    @GetMapping("/countByCustomer")
    public ResponseEntity<List<GetBookingsCountByCustomerResponse>> getCountBookingsByCustomer(){
        List<GetBookingsCountByCustomerResponse> response = getBookingsCountByCustomerUseCase.getCountBookingsByCustomer();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @GetMapping("/user/{userId}")
    public ResponseEntity<GetBookingsForUserResponse> getBookingsForUser(@PathVariable Long userId) {
        GetBookingsForUserResponse response = getBookingsForUserUseCase.getBookingsForUser(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed({"EVENT_ORGANIZER", "ADMINISTRATOR", "NORMAL_USER"})
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        deleteBookingUseCase.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}