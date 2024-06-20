package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Booking;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsForUserResponse {
    private List<Booking> bookings;
}
