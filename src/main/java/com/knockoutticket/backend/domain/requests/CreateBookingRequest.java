package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    @NotNull
    private Long customer_id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Long eventFightNightId;
}
