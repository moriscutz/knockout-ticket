package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Booking;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetBookingsForUserResponse {

    public List<Booking> bookings;
}
