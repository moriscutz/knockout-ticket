package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.TwoBoxersRequiredException;
import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.models.WeightClass;
import com.knockoutticket.backend.domain.requests.GetEventBoxersRequest;
import com.knockoutticket.backend.domain.responses.GetEventBoxersResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEventBoxersUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private GetEventBoxersUseCaseImpl getEventBoxersUseCase;

    @Test
    void getEventBoxers_ShouldReturnTwoBoxers() {
        // Arrange
        Long eventId = 1L;

        BoxerEntity boxerEntity1 = new BoxerEntity();
        boxerEntity1.setId(1L);
        boxerEntity1.setFullName("Boxer One");
        boxerEntity1.setWeightClass(WeightClass.HEAVYWEIGHT);
        boxerEntity1.setWins(10);
        boxerEntity1.setLosses(2);
        boxerEntity1.setDraws(1);
        boxerEntity1.setWeight(200.0f);
        boxerEntity1.setAge(30);

        BoxerEntity boxerEntity2 = new BoxerEntity();
        boxerEntity2.setId(2L);
        boxerEntity2.setFullName("Boxer Two");
        boxerEntity2.setWeightClass(WeightClass.MIDDLEWEIGHT);
        boxerEntity2.setWins(8);
        boxerEntity2.setLosses(3);
        boxerEntity2.setDraws(2);
        boxerEntity2.setWeight(160.0f);
        boxerEntity2.setAge(28);

        when(boxerRepository.findBoxersByEventId(eventId)).thenReturn(List.of(boxerEntity1, boxerEntity2));

        GetEventBoxersRequest request = new GetEventBoxersRequest();
        request.setEventId(eventId);

        // Act
        GetEventBoxersResponse response = getEventBoxersUseCase.getEventBoxers(request);

        // Assert
        Boxer boxer1 = response.getBoxer1();
        Boxer boxer2 = response.getBoxer2();

        assertEquals("Boxer One", boxer1.getFullName());
        assertEquals("Boxer Two", boxer2.getFullName());
        assertEquals(WeightClass.HEAVYWEIGHT, boxer1.getWeightClass());
        assertEquals(WeightClass.MIDDLEWEIGHT, boxer2.getWeightClass());
        assertEquals(10, boxer1.getWins());
        assertEquals(8, boxer2.getWins());
    }

    @Test
    void getEventBoxers_ShouldThrowTwoBoxersRequiredException() {
        // Arrange
        Long eventId = 1L;

        when(boxerRepository.findBoxersByEventId(eventId)).thenReturn(List.of(new BoxerEntity()));

        GetEventBoxersRequest request = new GetEventBoxersRequest();
        request.setEventId(eventId);

        // Act & Assert
        assertThrows(TwoBoxersRequiredException.class, () -> getEventBoxersUseCase.getEventBoxers(request));
    }
}
