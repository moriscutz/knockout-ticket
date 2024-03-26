package com.knockoutticket.backend.domain.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boxer extends AppUser {
    private String fullName;
    private WeightClass weightClass;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Float weight;
    private Integer age;
}
