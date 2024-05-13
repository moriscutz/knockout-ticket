package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;

public class BoxerConverter {

    private BoxerConverter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Boxer toBoxerDTO(BoxerEntity boxerEntity) {
        return Boxer.builder()
                .id(boxerEntity.getId())
                .fullName(boxerEntity.getFullName())
                .weightClass(boxerEntity.getWeightClass())
                .wins(boxerEntity.getWins())
                .losses(boxerEntity.getLosses())
                .draws(boxerEntity.getDraws())
                .weight(boxerEntity.getWeight())
                .age(boxerEntity.getAge())
                .build();
    }

    public static BoxerEntity toBoxerEntity(Boxer boxer) {
        return BoxerEntity.builder()
                .id(boxer.getId())
                .fullName(boxer.getFullName())
                .weightClass(boxer.getWeightClass())
                .wins(boxer.getWins())
                .losses(boxer.getLosses())
                .draws(boxer.getDraws())
                .weight(boxer.getWeight())
                .age(boxer.getAge())
                .build();
    }
}
