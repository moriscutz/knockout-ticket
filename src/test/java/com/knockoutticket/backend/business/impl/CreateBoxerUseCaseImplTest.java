package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.exception.*;
import com.knockoutticket.backend.domain.models.WeightClass;
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
            boxer.setId(1L);
            return boxer;
        });
    }

    @Test
    void createBoxer_SuccessfulCreation_ReturnsResponse() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, 10, 2, 1, 220.5f, 28
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
    void createBoxer_NullFullName_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                null, WeightClass.HEAVYWEIGHT, 10, 2, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(BlankBoxerFullNameException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullWeightClass_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", null, 10, 2, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(BlankWeightClassException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullWins_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, null, 2, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(BlankWinsException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullLosses_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, 10, null, 1, 220.5f, 28
        );

        // Act & Assert
        assertThrows(BlankLossesException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullDraws_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, 10, 2, null, 220.5f, 28
        );

        // Act & Assert
        assertThrows(BlankDrawsException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullWeight_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, 10, 2, 1, null, 28
        );

        // Act & Assert
        assertThrows(BlankWeightException.class, () -> createBoxerUseCase.createBoxer(request));
    }

    @Test
    void createBoxer_NullAge_ThrowsException() {
        // Arrange
        CreateBoxerRequest request = new CreateBoxerRequest(
                "John Doe", WeightClass.HEAVYWEIGHT, 10, 2, 1, 220.5f, null
        );

        // Act & Assert
        assertThrows(BlankAgeException.class, () -> createBoxerUseCase.createBoxer(request));
    }
}
