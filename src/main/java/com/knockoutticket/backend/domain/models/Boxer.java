package com.knockoutticket.backend.domain.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
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
    private Event nextEvent;
}
