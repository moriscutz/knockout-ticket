package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.springframework.stereotype.Component;

@Component
public class BoxerConverter {

    public BoxerEntity toEntity(Boxer boxer) {
        BoxerEntity boxerEntity = new BoxerEntity();
        boxerEntity.setId(boxer.getId());
        boxerEntity.setUsername(boxer.getUsername());
        boxerEntity.setPassword(boxer.getPassword());
        boxerEntity.setEmail(boxer.getEmail());
        boxerEntity.setUserType(boxer.getUserType());
        boxerEntity.setFullName(boxer.getFullName());
        boxerEntity.setWeightClass(boxer.getWeightClass());
        boxerEntity.setWins(boxer.getWins());
        boxerEntity.setLosses(boxer.getLosses());
        boxerEntity.setDraws(boxer.getDraws());
        boxerEntity.setWeight(boxer.getWeight());
        boxerEntity.setAge(boxer.getAge());
        return boxerEntity;
    }

    public Boxer toModel(BoxerEntity entity) {
        Boxer boxer = new Boxer();
        boxer.setId(entity.getId());
        boxer.setUsername(entity.getUsername());
        boxer.setPassword(entity.getPassword());
        boxer.setEmail(entity.getEmail());
        boxer.setUserType(entity.getUserType());
        boxer.setFullName(entity.getFullName());
        boxer.setWeightClass(entity.getWeightClass());
        boxer.setWins(entity.getWins());
        boxer.setLosses(entity.getLosses());
        boxer.setDraws(entity.getDraws());
        boxer.setWeight(entity.getWeight());
        boxer.setAge(entity.getAge());
        return boxer;
    }
}
