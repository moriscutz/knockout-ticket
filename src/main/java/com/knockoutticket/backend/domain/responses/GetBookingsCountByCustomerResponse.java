package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class GetBookingsCountByCustomerResponse {

    private AppUserEntity customer;
    private Long bookingCount;
}
