package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequest {

    @NotNull
    private Long customerId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Long eventFightNightId;
}
