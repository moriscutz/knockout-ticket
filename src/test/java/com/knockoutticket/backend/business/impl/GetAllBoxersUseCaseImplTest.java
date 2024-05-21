package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.domain.models.Boxer;
import com.knockoutticket.backend.domain.responses.GetBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllBoxersUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private GetAllBoxersUseCaseImpl getAllBoxersUseCase;

    @Test
    void getAllBoxers_ShouldReturnAllBoxers() {
        // Arrange
        BoxerEntity boxerEntity1 = new BoxerEntity();
        boxerEntity1.setId(1L);
        boxerEntity1.setFullName("Boxer One");

        BoxerEntity boxerEntity2 = new BoxerEntity();
        boxerEntity2.setId(2L);
        boxerEntity2.setFullName("Boxer Two");

        Boxer boxer1 = new Boxer();
        boxer1.setId(1L);
        boxer1.setFullName("Boxer One");

        Boxer boxer2 = new Boxer();
        boxer2.setId(2L);
        boxer2.setFullName("Boxer Two");

        when(boxerRepository.findAll()).thenReturn(Arrays.asList(boxerEntity1, boxerEntity2));

        // Act
        List<GetBoxerResponse> responses = getAllBoxersUseCase.getAllBoxers();

        // Assert
        assertEquals(2, responses.size());
        assertEquals("Boxer One", responses.get(0).getBoxer().getFullName());
        assertEquals("Boxer Two", responses.get(1).getBoxer().getFullName());

        verify(boxerRepository, times(1)).findAll();
    }
}
