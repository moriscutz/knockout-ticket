package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.persistence.ArchivedEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteArchivedEventsByBoxerUseCaseImplTest {

    @Mock
    private ArchivedEventRepository archivedEventRepository;

    @InjectMocks
    private DeleteArchivedEventsByBoxerUseCaseImpl deleteArchivedEventsByBoxerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteArchivedEventsByBoxer() {
        // Arrange
        Long boxerId = 1L;

        // Act
        deleteArchivedEventsByBoxerUseCase.deleteArchivedEventsByBoxer(boxerId);

        // Assert
        verify(archivedEventRepository, times(1)).deleteArchivedEventsByBoxerId(boxerId);
    }
}
