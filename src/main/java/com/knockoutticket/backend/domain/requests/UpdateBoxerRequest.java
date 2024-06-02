package com.knockoutticket.backend.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBoxerRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String fullName;

    @NotNull
    private Integer wins;

    @NotNull
    private Integer losses;

    @NotNull
    private Integer draws;
}
