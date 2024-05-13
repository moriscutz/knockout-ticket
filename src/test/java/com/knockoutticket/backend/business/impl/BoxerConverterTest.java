package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoxerConverterTest {

    @Test
    void testToBoxerDTO() {
        // Arrange
        BoxerEntity boxerEntity = BoxerEntity.builder()
                .id(1L)
                .fullName("John Doe")
                .weightClass(WeightClass.WELTERWEIGHT)
                .wins(20)
                .losses(2)
                .draws(1)
                .weight(147f)
                .age(30)
                .build();

        // Act
        Boxer boxer = BoxerConverter.toBoxerDTO(boxerEntity);

        // Assert
        assertNotNull(boxer);
        assertEquals(boxerEntity.getId(), boxer.getId());
        assertEquals(boxerEntity.getFullName(), boxer.getFullName());
        assertEquals(boxerEntity.getWeightClass(), boxer.getWeightClass());
        assertEquals(boxerEntity.getWins(), boxer.getWins());
        assertEquals(boxerEntity.getLosses(), boxer.getLosses());
        assertEquals(boxerEntity.getDraws(), boxer.getDraws());
        assertEquals(boxerEntity.getWeight(), boxer.getWeight());
        assertEquals(boxerEntity.getAge(), boxer.getAge());
    }

    @Test
    void testToBoxerEntity() {
        // Arrange
        Boxer boxer = Boxer.builder()
                .id(1L)
                .fullName("Jane Doe")
                .weightClass(WeightClass.LIGHTWEIGHT)
                .wins(15)
                .losses(1)
                .draws(2)
                .weight(135f)
                .age(28)
                .build();

        // Act
        BoxerEntity boxerEntity = BoxerConverter.toBoxerEntity(boxer);

        // Assert
        assertNotNull(boxerEntity);
        assertEquals(boxer.getId(), boxerEntity.getId());
        assertEquals(boxer.getFullName(), boxerEntity.getFullName());
        assertEquals(boxer.getWeightClass(), boxerEntity.getWeightClass());
        assertEquals(boxer.getWins(), boxerEntity.getWins());
        assertEquals(boxer.getLosses(), boxerEntity.getLosses());
        assertEquals(boxer.getDraws(), boxerEntity.getDraws());
        assertEquals(boxer.getWeight(), boxerEntity.getWeight());
        assertEquals(boxer.getAge(), boxerEntity.getAge());
    }
}
