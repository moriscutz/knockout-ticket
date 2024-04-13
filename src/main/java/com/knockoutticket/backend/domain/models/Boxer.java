package com.knockoutticket.backend.domain.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boxer {

    private Long id;
    private String fullName;
    private WeightClass weightClass;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Float weight;
    private Integer age;
    private Event nextEvent;
}
