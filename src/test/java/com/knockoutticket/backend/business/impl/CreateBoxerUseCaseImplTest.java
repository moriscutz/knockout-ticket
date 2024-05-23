package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.*;
import com.knockoutticket.backend.domain.requests.CreateBoxerRequest;
import com.knockoutticket.backend.domain.responses.CreateBoxerResponse;
import com.knockoutticket.backend.persistence.BoxerRepository;
import com.knockoutticket.backend.persistence.entity.BoxerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateBoxerUseCaseImplTest {

    @Mock
    private BoxerRepository boxerRepository;

    @InjectMocks
    private CreateBoxerUseCaseImpl createBoxerUseCase;

    @BeforeEach
    void setup() {
        lenient().when(boxerRepository.save(any(BoxerEntity.class))).thenAnswer(i -> {
            BoxerEntity boxer = i.getArgument(0);
            boxer.setId(1L); // Simulate ID generation
            return boxer;
        });
    }

    @Test
    void createBoxer_SuccessfulCreation_ReturnsResponse() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", "Heavyweight", 10, 2, 1, 220.5f, 28
        );

        // Act
        CreateBoxerResponse response = createBoxerUseCase.createBoxer(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("Boxer created successfully.", response.getMessage());
        verify(boxerRepository, times(1)).save(any(BoxerEntity.class));
    }

    @Test
    void createBoxer_MissingFullName_ThrowsIllegalArgumentException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "", "HEAVYWEIGHT", 10, 2, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> createBoxerUseCase.createBoxer(request));
        verify(boxerRepository, never()).save(any(BoxerEntity.class));
    }

    @Test
    void createBoxer_NullWeightClass_ThrowsIllegalArgumentException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", null, 10, 2, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> createBoxerUseCase.createBoxer(request));
        verify(boxerRepository, never()).save(any(BoxerEntity.class));
    }

    // Add more tests to cover other validation scenarios as needed
}
