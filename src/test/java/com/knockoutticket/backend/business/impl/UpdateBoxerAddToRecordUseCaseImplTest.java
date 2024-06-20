package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.InvalidBoxerRecordException;
import com.knockoutticket.backend.domain.requests.UpdateBoxerAddToRecordRequest;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateBoxerAddToRecordUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private UpdateBoxerAddToRecordUseCaseImpl updateBoxerAddToRecordUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateBoxerRecord() {
        // Arrange
        BoxerEntity boxer = new BoxerEntity();
        boxer.setWins(10);
        boxer.setLosses(2);
        boxer.setDraws(1);

        UpdateBoxerAddToRecordRequest request = new UpdateBoxerAddToRecordRequest();
        request.setWins(2);
        request.setLosses(1);
        request.setDraws(1);

        when(boxerRepository.findBoxerById(anyLong())).thenReturn(boxer);
        when(boxerRepository.save(any(BoxerEntity.class))).thenReturn(boxer);

        // Act
        updateBoxerAddToRecordUseCase.updateBoxerRecord(1L, request);

        // Assert
        verify(boxerRepository, times(1)).findBoxerById(1L);
        verify(boxerRepository, times(1)).save(boxer);
        assertEquals(12, boxer.getWins());
        assertEquals(3, boxer.getLosses());
        assertEquals(2, boxer.getDraws());
    }

    @Test
    void shouldThrowInvalidBoxerRecordExceptionForInvalidRecordValues() {
        // Arrange
        UpdateBoxerAddToRecordRequest request = new UpdateBoxerAddToRecordRequest();
        request.setWins(-1);
        request.setLosses(1);
        request.setDraws(1);

        // Act & Assert
        assertThrows(InvalidBoxerRecordException.class, () ->
                updateBoxerAddToRecordUseCase.updateBoxerRecord(1L, request)
        );

        // Verify no interactions with the repository
        verifyNoInteractions(boxerRepository);
    }
}
