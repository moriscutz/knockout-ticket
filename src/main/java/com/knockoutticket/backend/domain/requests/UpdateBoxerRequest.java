package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.WeightClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoxerRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private WeightClass weightClass;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Float weight;
    private Integer age;
    private Long nextEventId;
}
