package com.knockoutticket.backend.domain.requests;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoxerAddToRecordRequest {

    @NotNull
    private Long id;

    @NotNull
    private Integer wins;

    @NotNull
    private Integer losses;

    @NotNull
    private Integer draws;
}
