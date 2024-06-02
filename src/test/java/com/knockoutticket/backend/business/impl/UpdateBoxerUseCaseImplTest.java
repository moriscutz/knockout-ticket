package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.BoxerNotFoundException;
import com.knockoutticket.backend.domain.requests.UpdateBoxerRequest;
import com.knockoutticket.backend.domain.responses.UpdateBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateBoxerUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private UpdateBoxerUseCaseImpl updateBoxerUseCase;

    private BoxerEntity existingBoxer;

    @BeforeEach
    void setUp() {
        existingBoxer = new BoxerEntity();
        existingBoxer.setId(1L);
        existingBoxer.setFullName("Existing Boxer");
        existingBoxer.setWins(10);
        existingBoxer.setLosses(2);
        existingBoxer.setDraws(1);
    }

    @Test
    void updateBoxer_ShouldUpdateBoxerDetails_WhenBoxerExists() {
        // Arrange
        UpdateBoxerRequest request = new UpdateBoxerRequest();
        request.setId(1L);
        request.setFullName("Updated Boxer");
        request.setWins(15);
        request.setLosses(3);
        request.setDraws(2);

        when(boxerRepository.findById(request.getId())).thenReturn(Optional.of(existingBoxer));
        when(boxerRepository.save(any(BoxerEntity.class))).thenReturn(existingBoxer);

        // Act
        UpdateBoxerResponse response = updateBoxerUseCase.updateBoxer(request);

        // Assert
        assertEquals(request.getId(), response.getId());
        assertEquals(request.getFullName(), response.getFullName());
        assertEquals(request.getWins(), response.getWins());
        assertEquals(request.getLosses(), response.getLosses());
        assertEquals(request.getDraws(), response.getDraws());

        verify(boxerRepository, times(1)).findById(request.getId());
        verify(boxerRepository, times(1)).save(any(BoxerEntity.class));
    }

    @Test
    void updateBoxer_ShouldThrowException_WhenBoxerNotFound() {
        // Arrange
        UpdateBoxerRequest request = new UpdateBoxerRequest();
        request.setId(1L);
        request.setFullName("Updated Boxer");
        request.setWins(15);
        request.setLosses(3);
        request.setDraws(2);

        when(boxerRepository.findById(request.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BoxerNotFoundException.class, () -> updateBoxerUseCase.updateBoxer(request));

        verify(boxerRepository, times(1)).findById(request.getId());
        verify(boxerRepository, times(0)).save(any(BoxerEntity.class));
    }
}
