package com.knockoutticket.backend.persistence.entity;

import com.knockoutticket.backend.domain.models.WeightClass;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "boxers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_class")
    private WeightClass weightClass;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "losses")
    private Integer losses;

    @Column(name = "draws")
    private Integer draws;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "age")
    private Integer age;
}
