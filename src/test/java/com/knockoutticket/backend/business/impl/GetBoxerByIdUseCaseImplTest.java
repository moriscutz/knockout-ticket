package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetBoxerByIdUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private GetBoxerByIdUseCaseImpl getBoxerByIdUseCase;

    @Test
    void getBoxerById_ShouldReturnBoxer() {
        // Arrange
        Long boxerId = 1L;

        BoxerEntity boxerEntity = new BoxerEntity();
        boxerEntity.setId(boxerId);
        boxerEntity.setFullName("Boxer One");
        boxerEntity.setWeightClass(WeightClass.HEAVYWEIGHT);
        boxerEntity.setWins(10);
        boxerEntity.setLosses(2);
        boxerEntity.setDraws(1);
        boxerEntity.setWeight(200.0f);
        boxerEntity.setAge(30);

        when(boxerRepository.findBoxerById(boxerId)).thenReturn(boxerEntity);

        // Act
        GetBoxerResponse response = getBoxerByIdUseCase.getBoxerById(boxerId);

        // Assert
        Boxer boxer = response.getBoxer();
        assertEquals(boxerId, boxer.getId());
        assertEquals("Boxer One", boxer.getFullName());
        assertEquals(WeightClass.HEAVYWEIGHT, boxer.getWeightClass());
        assertEquals(10, boxer.getWins());
        assertEquals(2, boxer.getLosses());
        assertEquals(1, boxer.getDraws());
        assertEquals(200.0f, boxer.getWeight());
        assertEquals(30, boxer.getAge());
    }
}
