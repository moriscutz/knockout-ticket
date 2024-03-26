package com.knockoutticket.backend.persistence.entity;

import com.knockoutticket.backend.domain.models.WeightClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boxers")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxerEntity extends AppUserEntity {
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_class", nullable = false)
    private WeightClass weightClass;

    @Column(name = "wins", nullable = false)
    private Integer wins;

    @Column(name = "losses", nullable = false)
    private Integer losses;

    @Column(name = "draws", nullable = false)
    private Integer draws;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "age", nullable = false)
    private Integer age;
}
