package com.knockoutticket.backend.persistence.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "boxers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoxerEntity extends AppUserEntity {
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "weight_class")
    private String weightClass;

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
