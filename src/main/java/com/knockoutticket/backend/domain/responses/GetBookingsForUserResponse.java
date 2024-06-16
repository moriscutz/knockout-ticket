package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsForUserResponse {

    public List<Booking> bookings;
}
