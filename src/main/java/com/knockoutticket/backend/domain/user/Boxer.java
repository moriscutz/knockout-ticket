package com.knockoutticket.backend.domain.user;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Boxer extends AppUser {
    private String fullName;
    private WeightClass weightClass;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer noContests;
    private Integer age;
}
