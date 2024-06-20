package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteEventsByBoxerUseCase;
import com.knockoutticket.backend.persistence.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteEventsByBoxerUseCaseImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private DeleteEventsByBoxerUseCaseImpl deleteEventsByBoxerUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteEventsByBoxer() {
        // Arrange
        Long boxerId = 1L;

        // Act
        deleteEventsByBoxerUseCase.deleteEventsByBoxer(boxerId);

        // Assert
        verify(eventRepository, times(1)).deleteEventsByBoxer(boxerId);
    }
}