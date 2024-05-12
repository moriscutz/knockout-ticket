package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.WeightClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoxerRequest {
    @NotBlank
    private String fullName;

    @NotNull
    private WeightClass weightClass;

    @NotNull
    private Integer wins;

    @NotNull
    private Integer losses;

    @NotNull
    private Integer draws;

    @NotNull
    private Float weight;

    @NotNull
    private Integer age;
}
